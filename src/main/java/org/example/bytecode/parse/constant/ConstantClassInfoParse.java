package org.example.bytecode.parse.constant;

import org.example.bytecode.Parse;
import org.example.bytecode.Utils;

public class ConstantClassInfoParse extends ConstantInfoParse implements Parse {
    public int index;

    public ConstantClassInfoParse(int tag, String name) {
        super(tag, name);
    }


    @Override
    public int parse(int start, byte[] bytes) {
        index = Utils.getU2Int(start, bytes);
        start += 2;
        return start;
    }

    @Override
    public String toString() {
        return "ConstantClassInfoParse{" +
                "index=" + index +
                ", tag=" + tag +
                ", name=" + name +
                '}';
    }
}
