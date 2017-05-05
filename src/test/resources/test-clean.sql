DELETE FROM users;
DELETE FROM consumer_state;
DELETE FROM generator_state;
DELETE FROM power_station_state;

ALTER SEQUENCE consumer_state_consumer_state_id_seq RESTART WITH 100000;
ALTER SEQUENCE generator_state_generator_state_id_seq RESTART WITH 100000;
ALTER SEQUENCE power_station_state_power_station_state_id_seq RESTART WITH 100000;
ALTER SEQUENCE users_user_id_seq RESTART WITH 100000;