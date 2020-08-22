package com.troyforever.env.bean;

import java.util.Set;

public class Shed {
	
	private Integer id ;
	
	private String code ;
	
	private Base base ;
	
	private Set<UserShed> userSheds ;
	
	private Set<ShedCorp> shedCorps ;
	
	private Set<Setting> settings ;
	
	private Set<LogEnv> logEnvs ;
	
	private Set<LogOpe> logOpes ;

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

	public Set<ShedCorp> getShedCorps() {
		return shedCorps;
	}

	public void setShedCorps(Set<ShedCorp> shedCorps) {
		this.shedCorps = shedCorps;
	}

	public Set<Setting> getSettings() {
		return settings;
	}

	public void setSettings(Set<Setting> settings) {
		this.settings = settings;
	}

	public Set<LogEnv> getLogEnvs() {
		return logEnvs;
	}

	public void setLogEnvs(Set<LogEnv> logEnvs) {
		this.logEnvs = logEnvs;
	}

	public Set<LogOpe> getLogOpes() {
		return logOpes;
	}

	public void setLogOpes(Set<LogOpe> logOpes) {
		this.logOpes = logOpes;
	}
	
}
