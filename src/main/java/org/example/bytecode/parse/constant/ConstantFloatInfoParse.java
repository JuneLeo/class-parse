package org.example.bytecode.parse.constant;

import org.example.bytecode.Parse;
import org.example.bytecode.Utils;

public class ConstantFloatInfoParse extends ConstantInfoParse implements Parse {
    public float value;

    public ConstantFloatInfoParse(int tag, String name) {
        super(tag, name);
    }


    @Override
    public int parse(int start, byte[] bytes) {
        value = Utils.getU4Float(start, bytes);
        start += 4;
        return start;
    }

    @Override
    public String toString() {
        return "ConstantFloatInfoParse{" +
                "value=" + value +
                ", tag=" + tag +
                ", name=" + name +
                '}';
    }
}
