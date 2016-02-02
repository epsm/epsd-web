#Electric power system model
###Общие данные для всего проекта
Это простая модель выделенной электроэнергетической системы, состоящая из собственно модели и диспетчера. В модели моделируется работа оборудования электрической станции, участвующего в процессе поддержания частоты в энергосистеме и два вида потребителей нагрузки. Модель получает суточные графики генерации от диспетчера. Диспетчер расчитывает (на данный момент выдаёт заглушку) суточные графики генерации для электростанций в системе и получает от электростанций и потребителей мгновенные значения вырабатываемой и потребляемой мощности через заданные промежутки времени в модели. Приложение состоит из четырех пакетов [epsm-core](https://github.com/epsm/epsm-core), [epsm-web](https://github.com/epsm/epsm-web), [epsd-core](https://github.com/epsm/epsd-core) и [epsd-web](https://github.com/epsm/epsd-web). К каждому пакету прилагается описание. Приложение запущенно на двух серверах ([модель](http://model-epsm.rhcloud.com/) и [диспетчер](http://dispatcher-epsm.rhcloud.com/app/history)) взаимодействующих между собой с помощью обмена сообщениями в формате JSON и имеющих web-интерфейсы.

##epsd-web
#### описание пакета
Для того, чтобы понять что делает приложение, сначала нужно ознакомиться со следующим его пакетами:

1. описание пакета [epsm-core](https://github.com/epsm/epsm-core).
2. описание пакета [epsd-core](https://github.com/epsm/epsd-core)

epsd-web это однин из двух серверов распределенного приложения. Внутри сервера запущена модель диспетчера электроэнергетической системы из пакета [epsd-core](https://github.com/epsm/epsd-core). Сервер осуществляет приём и передачу сообщений  в формате JSON по протоколу http для модели диспетчера. 
Обмен сообщениями ведется с сервером [epsm-web](https://github.com/epsm/epsm-web), который в свою очередь является обёрткой для модели электроэнергетической системы из пакета [epsm-core](https://github.com/epsm/epsm-core).
Также сервер сохраняет данные в БД MySQL используя JPA и имеет web-интерфейc, который в виде графиков предоставляет данные о частоте, генерации и потреблении за последнеий полностью полученый от модели день.

серверы доступны по следующим ссылкам:

1. [сервер диспетчера электроэнергетической системы](http://dispatcher-epsm.rhcloud.com/app/history).
2. [сервер модели электроэнергетической системы](http://model-epsm.rhcloud.com/)

#### особености реализации

Для лучшего понимания следует посмотреть диаграмму класов в разделе особенности реализации пакета [epsd-core](https://github.com/epsm/epsd-core).

Внутри контейнера Spring c помощью фабрики создаётся bean, который реализует интерфейс Dispatcher и собственно является моделью диспетчера. Через этот интерфейс bean класса реализующего интерфейс IncomingMessageService передаёт полученные контроллерами сообщения от сервера модели. Диспетчер отпраляет сообщения модели через bean, реализующий интерфейс OutgoingMessageService, который в свою очередь наследует интерфейс ObjectsConnector с которым работает диспетчер. Интерфейс, необходимый диспетчеру для сохранения состояний модели (StateSaver) наследуется интерфейсом PowerObjectService. Bean, реализующий этот интерфейс через интерфейсы репозиториев сохраняет состояния. При запросе данных для построения графиков bean класса ChartService запрашивает данные у соответствующих bean'ов реализующих интерфейсы репозиториев.

Сохранение и получени данных реализованно через интерфейсы JPA. В качестве реализации JPA используется Hibernate. В качестве БД используется MySQL.

Полученные от модели данные сохраняются в двух сущностях SavedConsumerState и SavedGeneratorState, уоторые отображаются на таблицы  consumer_state и generator_state. Данные для построения графиков извлекаются с помощью сущностей Frequencqy, TotalGeneration и TotalConsumption которые отображаются на view'ы frequecy, total\_generation и total\_consumption соответственно.
View для частоты определен как: извлечь все значения частоты для для кадой даты и каждого времени без повторений из таблицы generator\_state.
View avaible date определено как: извлечь все даты, которые одновременно есть в обоих таблицах consumer\_state и generator\_state.
View для полной генерации и потребления определены как: извлечь сумму всех значений (соответственно генерации или потребления) для каждого уникального сочетания даты и времени. Схема базы данных создаётся (если необходимо) при загрузке приложения путём выполнения sql скрипта [schema.sql](https://github.com/epsm/epsd-web/blob/master/src/main/resources/schema.sql). На таблицах consumer\_state и generator\_state имеются уникальные индексы, исключающие возможность вставки в таблицу записей имеющих не уникальное сочетание даты, времени и id объекта. 
Также имеется таблица user, которая используется фреймворком Spring security для аутентификации пользователей, получающих доступ к странице с графиками.

+ схема БД: 
![db_schema](https://cloud.githubusercontent.com/assets/16285736/12760202/f1dd2378-c9ed-11e5-9b3c-9305d0c5093c.jpg)

#### технологии
Spring webmvc, Spring security, JPA (Hibernate), MySQL, JSON, SLF4J, Logback, Junit, Mockito, DbUnit, Spring test.

Покрытие unit-тестами согласно EclEmma 78,3%. В основном статистику портит то, что тестами не покрыты @Configuration классы и класс UrlRequestSender.