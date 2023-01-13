package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

public class ConstantLongInfoParse extends ConstantInfoParse implements Parse {
    public long value;

    public ConstantLongInfoParse(int tag, String name) {
        super(tag, name);
    }


    @Override
    public int parse(int start, byte[] bytes) {
        value = Utils.getU8Long(start, bytes);
        start += 8;
        return start;
    }

    @Override
    public String toString() {
        return "ConstantLongInfoParse{" +
                "value=" + value +
                ", tag=" + tag +
                ", name=" + name +
                '}';
    }
}
