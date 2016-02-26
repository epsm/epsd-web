#Electric power system model
###General data for the project
This is the simple model of a dedicated electric power system. The model consist of two separate parts. There are model and dispatcher. In the model simulated working of power stations equipment, that participate in process of supporting the frequency in system.
The model gets daily generation schedules from dispatcher. Dispatcher gets data from power stations, consumers and calculates (for now gives stub) daily generation schedules.

Application consist of four packages, two models:

+ [epsm-core](https://github.com/epsm/epsm-core)
+ [epsd-core](https://github.com/epsm/epsd-core)

and two wrappers for models that helps intarcting models using JSON. Also wrappers have web interfaces.

+ [epsd-web](https://github.com/epsm/epsd-web)
+ [epsm-web](https://github.com/epsm/epsm-web)


Application launched on two servers on OpenShift:

+ [model](http://model-epsm.rhcloud.com/)
+ [dispatcher](http://dispatcher-epsm.rhcloud.com/app/history). 

The total project size is more than 16,000 source lines of code.

| technology       | [epsm-core](https://github.com/epsm/epsm-core) | [epsm-web](https://github.com/epsm/epsm-web) | [epsd-core](https://github.com/epsm/epsd-core) | [epsd-web](https://github.com/epsm/epsd-web)|
|:-----------------|:---:|:---:|:---:|:---:|
| Java core        | yes | yes | yes | yes |
| Spring core      | no  | yes | no  | yes |
| MySQL            | no  | no  | no  | yes |
| JPA (Hibernate)  | no  | no  | no  | yes |
| JSP              | no  | yes | no  | yes |
| Spring webmvc    | no  | yes | no  | yes |
| JSON (fasterxml) | yes | yes | no  | yes |
| HTML, CSS        | no  | yes | no  | yes |
| Spring security  | no  | no  | no  | yes |
| SLF4J, Logback   | yes | yes | yes | yes |
| Junit, Mockito   | yes | yes | yes | yes |
| Power Mockito    | yes | yes | no  | no  |
| Spring test      | no  | yes | no  | yes |
| DbUnit           | no  | no  | no  | yes |
| CI (Travis)      | [![Build Status](https://travis-ci.org/epsm/epsm-core.svg?branch=master)](https://travis-ci.org/epsm/epsm-core) | [![Build Status](https://travis-ci.org/epsm/epsm-web.svg?branch=master)](https://travis-ci.org/epsm/epsm-web) | [![Build Status](https://travis-ci.org/epsm/epsd-core.svg?branch=master)](https://travis-ci.org/epsm/epsd-core) | [![Build Status](https://travis-ci.org/epsm/epsd-web.svg?branch=master)](https://travis-ci.org/epsm/epsd-web) |

##epsd-web
#### package description
To understand what does project do you have to read following firstly:

1. subject area description for [epsm-core](https://github.com/epsm/epsm-core)
2. subject area description for [epsd-core](https://github.com/epsm/epsd-core)


epsd-web is one of the two servers of the distributed application. Inside the server run the model of the electric power system dispatcher from the package [epsd-core](https://github.com/epsm/epsd-core). The server performs receiving and transmitting messages in JSON format over http for the dispatcher model. 
Messaging is performing with the server [epsm-web](https://github.com/epsm/epsm-web), which  is a wrapper for the model of power system from package [epsm-core](https://github.com/epsm/epsm-core).
The server stores data in MySQL database using JPA and has a web interface, which provides information about the frequency of generation and consumption for the last fully received from the model day as charts.

#### realization

It will be better to see class diagrams in realization chapter of [epsd-core](https://github.com/epsm/epsd-core) to understand more clearly.

Inside the Spring container creates bean that implements the Dispatcher interface. This bean  actually is the model of power system dispatcher. Through Dispatcher interface the bean that implements the IncomingMessageService interface passes messages, received from model server to dispatcher. Dispatcher sends messages to the model using bean that implements the OutgoingMessageService interface, which  inherited  from the ObjectsConnector interface. The interface needed for the dispatcher to save the model States (StateSaver) is implemented by PowerObjectService. Bean that implements this interface using the repositories interfaces to save States. When querying data for charts, bean of the class ChartService requests data from relevant repositories.

Saving and getting data is implemented using the JPA interfaces. As the implementation of JPA using Hibernate. As database MySQL is used.

Obtained from the model data is stored in the two entities SavedConsumerState and SavedGeneratorState. All the necessary data for charts obtains from the DB views.

Spring security is used to authenticate users that access a page with graphs.

+ DB schema: 
![db_schema](https://cloud.githubusercontent.com/assets/16285736/12760202/f1dd2378-c9ed-11e5-9b3c-9305d0c5093c.jpg)

Unit-test coverage according to EclEmma is 83,5%.