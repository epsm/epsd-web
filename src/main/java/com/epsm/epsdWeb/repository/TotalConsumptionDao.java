package com.epsm.epsdWeb.repository;

import java.time.LocalDate;
import java.util.List;

import com.epsm.epsdWeb.domain.TotalConsumption;

public interface TotalConsumptionDao {
	List<TotalConsumption> getTotalConsumptions(LocalDate date);
}
