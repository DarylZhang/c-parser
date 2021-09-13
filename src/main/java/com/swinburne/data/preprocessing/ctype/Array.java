package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Array extends Pointer
{
	private int capacity;

	public Array() {}

	public Array(Type t, int n)
	{
		super(t);
		capacity = n;
		this.setTypename("array");
		this.setSize(n * t.getSize());
	}

}