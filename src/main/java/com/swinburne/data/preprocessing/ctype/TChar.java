package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public final class TChar extends Type
{
	public TChar()
	{
		this.setTypename("char");
		this.setSize(4);
	};
	
}