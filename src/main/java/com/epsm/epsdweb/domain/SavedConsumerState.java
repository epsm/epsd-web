package com.epsm.epsdweb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@ToString
@Entity
@Table(name="consumer_state")
public class SavedConsumerState extends AbstractEntity {

	@Column(name="load_in_mw")
	private float loadInMW;
}
