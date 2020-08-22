package com.refen;

import java.util.Date;

public class LogEnv {

	private Integer id ;
	
	private Integer type ;
	
	private Double value ;
	
	private Date time ;
	
	private Shed shed ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Shed getShed() {
		return shed;
	}

	public void setShed(Shed shed) {
		this.shed = shed;
	}
}
