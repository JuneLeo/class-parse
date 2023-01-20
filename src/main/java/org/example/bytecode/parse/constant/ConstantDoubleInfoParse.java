package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

public class ConstantDoubleInfoParse extends ConstantInfoParse implements Parse {
    public double value;

    public ConstantDoubleInfoParse(int tag, String name) {
        super(tag, name);
    }


    @Override
    public int parse(int start, byte[] bytes) {
        value = Utils.getU8Double(start, bytes);
        start += 8;
        return start;
    }

    @Override
    public String toString() {
        return "[ " +"name=" + name +
                ", value=" + value + " ]";
    }
}
