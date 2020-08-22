package com.troyforever.env.bean;

//大棚信息类
public class Shed {
	
	//大棚ID
	private Integer id ;
	
	//大棚编码
	private String code ;
	
	//大棚内温度信息
	private Double temp ;
	
	//大棚内光照信息
	private Double light ;
	
	//大棚内湿度信息
	private Double humi ;
	
	//大棚内CO2气体浓度
	private Double gas ;
	
	//大棚室外的温度
	private Double outtemp ;
	
	private Base base ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getLight() {
		return light;
	}

	public void setLight(Double light) {
		this.light = light;
	}

	public Double getHumi() {
		return humi;
	}

	public void setHumi(Double humi) {
		this.humi = humi;
	}

	public Double getGas() {
		return gas;
	}

	public void setGas(Double gas) {
		this.gas = gas;
	}

	public Double getOuttemp() {
		return outtemp;
	}

	public void setOuttemp(Double outtemp) {
		this.outtemp = outtemp;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}
	
	
}
