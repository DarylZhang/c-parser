package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Vector;

@Data
@EqualsAndHashCode(callSuper=false)
public final class Union extends Record
{
	public Union(String n, Vector<TValue> t, Vector<String> s)
	{
		this.setName(n);
		this.setVt(t);
		this.setVs(s);
	}
	public Union(String n)
	{
		this.setName(n);
	}
} 