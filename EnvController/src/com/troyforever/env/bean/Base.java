package com.troyforever.env.bean;

import java.util.Set;

//基地信息类
public class Base {
	
	//基地ID
	private Integer id ;
	
	//基地编码
	private String name ;
	
	//基地地址
	private String address ;
	
	private Set sheds ;

	public Set getSheds() {
		return sheds;
	}

	public void setSheds(Set sheds) {
		this.sheds = sheds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
