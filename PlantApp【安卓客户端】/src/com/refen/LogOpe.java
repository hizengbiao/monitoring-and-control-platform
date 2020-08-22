package com.refen;

import java.util.Date;

public class LogOpe {

	private Integer id ;
	
	private String operate ;
	
	private Date time ;
	
	private User user ;
	
	private Shed shed ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shed getShed() {
		return shed;
	}

	public void setShed(Shed shed) {
		this.shed = shed;
	}
	
}
