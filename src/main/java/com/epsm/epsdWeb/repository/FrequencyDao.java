package com.epsm.epsdWeb.repository;

import java.time.LocalDate;
import java.util.List;

import com.epsm.epsdWeb.domain.Frequency;

public interface FrequencyDao {
	List<Frequency> getFrequencies(LocalDate date);
}
