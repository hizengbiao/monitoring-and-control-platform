package com.troyforever.env.bean;

import java.util.Set;

public class Corp {

	private Integer id ;
	
	private String name ;
	
	private String code ;
	
	private Set<ShedCorp> shedCorps ;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<ShedCorp> getShedCorps() {
		return shedCorps;
	}

	public void setShedCorps(Set<ShedCorp> shedCorps) {
		this.shedCorps = shedCorps;
	}
	
}
