package com.swinburne.data.preprocessing.ctype;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class StructVariable extends Record{

//    private int location;
//    private int offset;
    private boolean pointer;

    public StructVariable(String name) {
        this.setName(name);
    }

    public StructVariable(String name, String typename) {
        this.setName(name);
        this.setTypename(typename);
    }
}
