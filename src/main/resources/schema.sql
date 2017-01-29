CREATE TABLE IF NOT EXISTS generator_state (
	id SERIAL,
	power_object_date date NOT NULL,
	power_object_time time NOT NULL,
	power_object_id BIGINT,
	real_timestamp TIMESTAMP NOT NULL,
	frequency float,
	generation_in_mw float,
	generator_number INT,
	PRIMARY KEY (id),
	CONSTRAINT oneEntryInOneMomentForGenerator UNIQUE (power_object_id, generator_number, power_object_date, power_object_time)
);

CREATE TABLE IF NOT EXISTS consumer_state (
	id SERIAL,
	power_object_date date NOT NULL,
	power_object_time time NOT NULL,
	power_object_id BIGINT,
	real_timestamp TIMESTAMP NOT NULL,
	load_in_mw FLOAT,
	PRIMARY KEY (id),
	CONSTRAINT oneEntryInOneMomentForConsumer UNIQUE (power_object_id,power_object_date,power_object_time)
);

CREATE TABLE IF NOT EXISTS users (
	id SERIAL,
	name VARCHAR(20) NOT NULL,
	password VARCHAR(100) NOT NULL,
	email VARCHAR(30),
	role VARCHAR(10) NOT NULL,
	PRIMARY KEY (id)
);

CREATE OR REPLACE VIEW total_generation AS SELECT
	power_object_date, power_object_time, sum(generation_in_mw) AS total_generation_in_mw
	FROM generator_state GROUP BY power_object_date, power_object_time;

CREATE OR REPLACE VIEW total_consumption AS SELECT
	power_object_date, power_object_time, sum(load_in_mw) AS total_consumption_in_mw
	FROM consumer_state GROUP BY power_object_date, power_object_time;

CREATE OR REPLACE VIEW frequency AS SELECT
    power_object_date, power_object_time, frequency
	FROM generator_state GROUP BY power_object_date, power_object_time, frequency;

CREATE OR REPLACE VIEW avaible_date AS SELECT power_object_date
	FROM generator_state WHERE power_object_date IN (
	SELECT power_object_date FROM consumer_state)
	GROUP BY power_object_date;
