package com.swinburne.data.preprocessing.ctype;

import lombok.Data;

@Data
public abstract class Type
{
	public Type(){};

	private String typename;
	private int size;
	private Boolean isPointer = false;
	private Boolean isArray = false;
}