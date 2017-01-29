package com.epsm.epsdWeb.domain;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="avaible_date")
public class AvaibleDate {

	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="power_object_date", updatable=false)
	private Date date;
	
	public Date getDate(){
		return date;
	}
	
	@Override
	public String toString(){
		return String.format("AvaibleDate date: %s", date);
	}
}
