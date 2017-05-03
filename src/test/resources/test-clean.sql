DELETE FROM users;
DELETE FROM power_station_state;
DELETE FROM consumer_state;

ALTER SEQUENCE users_user_id_seq RESTART WITH 100000;
ALTER SEQUENCE consumer_state_id_seq RESTART WITH 100000;
ALTER SEQUENCE power_station_state_id_seq RESTART WITH 100000;