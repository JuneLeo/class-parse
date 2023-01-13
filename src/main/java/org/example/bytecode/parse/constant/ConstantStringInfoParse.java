package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

public class ConstantStringInfoParse extends ConstantInfoParse implements Parse {
    int stringIndex;

    public ConstantStringInfoParse(int tag, String name) {
        super(tag, name);
    }

    @Override
    public int parse(int start, byte[] bytes) {
        stringIndex = Utils.getU2Int(start, bytes);
        start += 2;
        return start;
    }

    @Override
    public String toString() {
        return "ConstantStringInfoParse{" +
                "index=" + stringIndex +
                ", tag=" + tag +
                ", name=" + name +
                '}';
    }
}
