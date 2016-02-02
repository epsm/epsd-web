CREATE TABLE IF NOT EXISTS generator_state (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	power_object_date date NOT NULL,
	power_object_time time NOT NULL,
	power_object_id bigint(20),
	real_timestamp datetime NOT NULL,
	frequency float,
	generation_in_mw float,
	generator_number int(11),
	PRIMARY KEY (id),
	UNIQUE KEY OneEntryInOneMomentForGenerator (power_object_id,generator_number,power_object_date,power_object_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS consumer_state (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	power_object_date date NOT NULL,
	power_object_time time NOT NULL,
	power_object_id bigint(20),
	real_timestamp datetime NOT NULL,
	load_in_mw float,
	PRIMARY KEY (id),
	UNIQUE KEY OneEntryInOneMomentForConsumer (power_object_id,power_object_date,power_object_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	password VARCHAR(100) NOT NULL,
	email VARCHAR(30),
	role VARCHAR(10) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE OR REPLACE VIEW total_generation AS SELECT
	id, power_object_date, power_object_time, sum(generation_in_mw) AS total_generation_in_mw
	FROM generator_state GROUP BY power_object_date, power_object_time;

CREATE OR REPLACE VIEW total_consumption AS SELECT
	id, power_object_date, power_object_time, sum(load_in_mw) AS total_consumption_in_mw
	FROM consumer_state GROUP BY power_object_date, power_object_time;

CREATE OR REPLACE VIEW frequency AS SELECT id, power_object_date, power_object_time, frequency 
	FROM generator_state GROUP BY power_object_date, power_object_time;

CREATE OR REPLACE VIEW avaible_date AS SELECT id, power_object_date
	FROM generator_state WHERE power_object_date IN (
	SELECT power_object_date FROM consumer_state)
	GROUP BY power_object_date;
