package com.epsm.epsdweb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long powerObjectId;
	
	@Column(name="simulation_timestamp", nullable=false)
	private LocalDateTime simulationTimeStamp;

	@Column(name="real_timestamp", nullable=false)
	private LocalDateTime realTimeStamp;
}
