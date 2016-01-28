package com.epsm.epsdWeb.repository;

import java.time.LocalDate;
import java.util.List;

import com.epsm.epsdWeb.domain.ValueSource;

public interface TotalGenerationDao {
	List<ValueSource> getTotalGenerations(LocalDate date);
}
