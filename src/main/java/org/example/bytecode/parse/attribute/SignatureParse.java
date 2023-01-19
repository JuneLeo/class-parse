package org.example.bytecode.parse.attribute;

import org.example.bytecode.parse.ConstantParse;

public class SignatureParse extends AttributeFormatParse{
    private ConstantParse constantParse;
    public SignatureParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        return 0;
    }
}
