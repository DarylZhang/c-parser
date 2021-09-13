package com.swinburne.data.preprocessing.ctype;

import java.util.Vector;

public final class Function extends Type {
    public Function(Type t, Vector<TValue> a, String n) {
        this.returnType = t;
        this.argument = a;
        this.name = n;
        this.setTypename("function");
        this.setSize(0);
    }

    ;
    private Vector<TValue> argument;
    private Type returnType;
    private String name;
} 
