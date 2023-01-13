package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

public class ConstantMethodTypeInfoParse extends ConstantInfoParse implements Parse {
    int descriptorIndex;

    public ConstantMethodTypeInfoParse(int tag, String name) {
        super(tag, name);
    }


    @Override
    public int parse(int start, byte[] bytes) {
        descriptorIndex = Utils.getU2Int(start, bytes);
        start += 2;
        return start;
    }


    @Override
    public String toString() {
        return "ConstantMethodTypeInfoParse{" +
                "descriptorIndex=" + descriptorIndex +
                ", tag=" + tag +
                ", name=" + name +
                '}';
    }
}
