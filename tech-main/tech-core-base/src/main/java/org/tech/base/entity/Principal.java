package org.tech.base.entity;

import java.io.Serializable;

public class Principal implements Serializable {
	private static final long serialVersionUID = 8115913843916393747L;

	private int id;

	private String account;

	private String name;
	
	private String host;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return name + "[" + account + "]";
	}
}
