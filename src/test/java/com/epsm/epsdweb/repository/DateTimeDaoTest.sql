INSERT INTO consumer_state (id, power_object_id, simulation_timestamp, real_timestamp, load_in_mw)
VALUES (1, 1, '2000-01-01 00:00:00', '1980-09-09 09:30:25', 10.0),
	   (2, 1, '2001-01-01 00:00:00', '1980-09-09 09:30:25', 10.0);

INSERT INTO power_station_state (id, power_object_id, simulation_timestamp, real_timestamp, frequency, generation_in_mw)
VALUES (1, 1, '2002-01-01 00:00:00', '1980-09-09 09:30:25', 10.0, 20.0),
	   (2, 1, '2003-01-01 00:00:00', '1980-09-09 09:30:25', 10.0, 20.0);