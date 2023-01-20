package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

public class ConstantIntegerInfoParse extends ConstantInfoParse implements Parse {
    public int value;

    public ConstantIntegerInfoParse(int tag, String name) {
        super(tag, name);
    }


    @Override
    public int parse(int start, byte[] bytes) {
        value = Utils.getU4Int(start, bytes);
        start += 4;
        return start;
    }

    @Override
    public String toString() {
        return "[ " +"name=" + name +
                ", value=" + value + " ]";
    }
}
