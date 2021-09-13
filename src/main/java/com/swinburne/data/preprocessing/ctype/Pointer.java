package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Pointer extends Type {

    public Pointer() {
    }

    public Pointer(Type t) {
        this.setElementType(t);
        this.setTypename("pointer");
        this.setSize(4);
    }

    public Pointer(Pointer p) {
        this.elementType = p.elementType;
        this.setTypename("pointer");
        this.setSize(4);
    }

    private Type elementType;
}