package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TVariable extends Record
{
	public TVariable(String ref)
	{
		this.setTypename("int");
		this.setRef(ref);
	};

	@Override
	public String toString() {
		return this.getTypename() + " " + this.getRef();
	}
}