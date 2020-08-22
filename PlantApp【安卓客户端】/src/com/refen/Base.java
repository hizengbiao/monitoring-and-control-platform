package com.refen;

import java.util.Set;

public class Base {
	
	private Integer id ;
	
	private String name ;
	
	private String address  ;
	
	Set<Shed> sheds ;
	
	Set<User> users ;

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

	public Set<Shed> getSheds() {
		return sheds;
	}

	public void setSheds(Set<Shed> sheds) {
		this.sheds = sheds;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
