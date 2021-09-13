package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public final class TName extends Type
{
	private String str;

	public TName(String s)
	{
		this.str = s;
		this.setTypename("name");
		this.setSize(4);
	}

}