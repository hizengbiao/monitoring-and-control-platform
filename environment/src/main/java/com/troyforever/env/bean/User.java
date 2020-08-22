package com.troyforever.env.bean;

import java.util.Set;

public class User {

	private Integer id ;
	
	private String name ;
	
	private String phone ;
	
	private String username ;
	
	private String password ;
	
	private Integer isAdmin ;
	
	private Base base ;
	
	private Set<UserShed> userSheds ;
	
	private Set<Setting> settings ;
	
	private Set<LogOpe> logOpes ;

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Set<UserShed> getUserSheds() {
		return userSheds;
	}

	public void setUserSheds(Set<UserShed> userSheds) {
		this.userSheds = userSheds;
	}

	public Set<Setting> getSettings() {
		return settings;
	}

	public void setSettings(Set<Setting> settings) {
		this.settings = settings;
	}

	public Set<LogOpe> getLogOpes() {
		return logOpes;
	}

	public void setLogOpes(Set<LogOpe> logOpes) {
		this.logOpes = logOpes;
	}
	
	
	
}
