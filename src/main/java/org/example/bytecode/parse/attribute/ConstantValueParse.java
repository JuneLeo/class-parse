package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;

public class ConstantValueParse extends AttributeFormatParse {
    private ConstantParse constantParse;
    public int constantValueIndex;

    public ConstantValueParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        constantValueIndex = Utils.getU2Int(start, code);
        start += 2;
        System.out.println("ConstantValue:");
        System.out.println(this);
        return start;
    }

    @Override
    public String toString() {
        return Utils.getUtf(constantValueIndex, constantParse);
    }
}
