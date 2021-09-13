package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Vector;

@Data
@EqualsAndHashCode(callSuper=false)
public abstract class Record extends Type {
	private Vector<TValue> vt;
	private Vector<String> vs;
	private String name;
	private boolean isunion;
	
} 
