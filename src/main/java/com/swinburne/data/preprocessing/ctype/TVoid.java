package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public final class TVoid extends Type
{
	public TVoid()
	{
		this.setTypename("void");
		this.setSize(0);
	};
}