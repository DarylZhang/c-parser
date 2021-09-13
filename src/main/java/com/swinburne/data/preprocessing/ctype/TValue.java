package com.swinburne.data.preprocessing.ctype;

import lombok.Data;

@Data
public class TValue {

    private Type type;
    private Location location;

    public TValue(Type t, Location a) {
        this.type = t;
        this.location = a;
    }

    public TValue() {
    }
}