package com.epsm.epsdWeb.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.epsm.epsdWeb.domain.TotalGeneration;

public interface TotalGenerationDao {
	List<TotalGeneration> getTotalGenerations(LocalDate date);
}
