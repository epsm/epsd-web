CREATE TABLE IF NOT EXISTS power_station_state (
	id SERIAL,
	power_object_id BIGINT,
	simulation_timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	real_timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	frequency FLOAT,
	generation_in_mw FLOAT,

	CONSTRAINT power_station_state_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS consumer_state (
	id SERIAL,
	power_object_id BIGINT,
	simulation_timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	real_timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	load_in_mw FLOAT,

	CONSTRAINT consumer_state_pk PRIMARY KEY (id)
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