package org.example.bytecode.parse.constant;

public abstract class ConstantInfoParse {
    public int tag;
    public String name;

    public ConstantInfoParse(int tag, String name) {
        this.tag = tag;
        this.name = name;
    }
}
