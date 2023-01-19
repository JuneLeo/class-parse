package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;

public class SignatureParse extends AttributeFormatParse {
    private ConstantParse constantParse;
    public int signatureIndex;

    public SignatureParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        signatureIndex = Utils.getU2Int(start, code);
        start += 2;
        System.out.println("Signature:");
        System.out.println(this);
        return start;
    }

    @Override
    public String toString() {
        return Utils.getUtf(signatureIndex, constantParse);
    }
}
