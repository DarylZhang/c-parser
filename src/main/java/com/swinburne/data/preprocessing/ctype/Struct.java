package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Vector;

@Data
@EqualsAndHashCode(callSuper=false)
public final class Struct extends Record
{
	private String ref;
	private Vector<Record> structChildren;

	public Struct(String n, Vector<TValue> t, Vector<String> s)
	{
		this.setName(n);;
		this.setVt(t);
		this.setVs(s);

		this.setTypename("struct");
		this.setSize(0);

		this.setIsunion(false);
		for(int i=0;i<t.size();i++)
		{
			this.setSize(this.getSize() + t.get(i).getType().getSize());
			if (t.get(i).getType().getTypename().equals("array"))
				this.setSize(this.getSize() + 4);
		}
	}
	public Struct(String n)
	{
		this.setName(n);;
		this.setTypename("struct");
	}
}
