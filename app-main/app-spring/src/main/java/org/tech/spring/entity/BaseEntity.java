package org.tech.spring.entity;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	public abstract String toLogString();
}
