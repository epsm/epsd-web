INSERT INTO power_station_state (power_station_state_id, power_station_id, simulation_timestamp, frequency)
VALUES (1, 1, '2000-01-01 00:00:00', 10.0),
	   (2, 1, '2010-01-01 00:00:00', 10.0),
	   (3, 1, '2015-01-01 00:00:00', 10.0),
	   (4, 2, '2015-01-01 00:00:00', 10.0),
	   (5, 1, '2020-01-01 00:00:00', 10.0),
	   (6, 1, '2030-01-01 00:00:00', 10.0);

INSERT INTO generator_state (generator_state_id, power_station_state_id, generator_number, generation_in_mw)
VALUES (1, 1, 1, 10.0),
	   (2, 2, 1, 11.1),
	   (3, 2, 2, 12.2),
	   (4, 3, 1, 13.3),
	   (5, 3, 2, 14.4),
	   (6, 4, 5, 15.5),
	   (7, 5, 8, 16.6),
	   (8, 6, 1, 17.7);