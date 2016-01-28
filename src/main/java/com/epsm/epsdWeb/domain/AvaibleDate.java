package com.epsm.epsdWeb.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="avaible_date")
public class AvaibleDate {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="power_object_date", nullable=false)
	private Date date;
	
	public Date getDate(){
		return date;
	}
	
	@Override
	public String toString(){
		return String.format("AvaibleDate date: %s", date);
	}
}
