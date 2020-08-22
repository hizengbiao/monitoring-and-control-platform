package com.troyforever.env.bean;

public class Setting {

	private Integer id ;
	
	private double temp ;
	
	private double light ; 
	
	private double humi ; 
	
	private double gas ;
	
	private Shed shed ;
	
	private User user ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getLight() {
		return light;
	}

	public void setLight(double light) {
		this.light = light;
	}

	public double getHumi() {
		return humi;
	}

	public void setHumi(double humi) {
		this.humi = humi;
	}

	public double getGas() {
		return gas;
	}

	public void setGas(double gas) {
		this.gas = gas;
	}

	public Shed getShed() {
		return shed;
	}

	public void setShed(Shed shed) {
		this.shed = shed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
