CREATE TABLE IF NOT EXISTS consumer_state (
	consumer_state_id SERIAL,
	consumer_id BIGINT,
	simulation_timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	load_in_mw FLOAT,

	CONSTRAINT consumer_state_pk PRIMARY KEY (consumer_state_id)
);

CREATE TABLE IF NOT EXISTS power_station_state (
	power_station_state_id SERIAL,
	power_station_id BIGINT,
	simulation_timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	frequency FLOAT,

	CONSTRAINT power_station_state_pk PRIMARY KEY (power_station_state_id)
);

CREATE TABLE IF NOT EXISTS generator_state (
	generator_state_id SERIAL,
	power_station_state_id BIGINT NOT NULL,
	generator_number INT NOT NULL,
	generation_in_mw FLOAT NOT NULL,

	CONSTRAINT generator_state_pk PRIMARY KEY (generator_state_id),
	CONSTRAINT generator_state_power_station_state_fk FOREIGN KEY (power_station_state_id)
      REFERENCES power_station_state (power_station_state_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS users (
	user_id SERIAL,
	name VARCHAR(20) NOT NULL,
	password VARCHAR(100) NOT NULL,
	email VARCHAR(30),
	role VARCHAR(10) NOT NULL,

	CONSTRAINT users_pk PRIMARY KEY (user_id),
    CONSTRAINT users_email_unq UNIQUE (email)
);