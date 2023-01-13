package org.example.bytecode.parse.constant;

import org.example.bytecode.Parse;
import org.example.bytecode.Utils;

public class ConstantNameAndTypeInfoParse extends ConstantInfoParse implements Parse {
    int nameIndex;
    int signatureIndex;

    public ConstantNameAndTypeInfoParse(int tag, String name) {
        super(tag, name);
    }

    @Override
    public int parse(int start, byte[] bytes) {
        nameIndex = Utils.getU2Int(start, bytes);
        start += 2;
        signatureIndex = Utils.getU2Int(start, bytes);
        start += 2;
        return start;
    }

    @Override
    public String toString() {
        return "ConstantNameAndTypeInfoParse{" +
                "nameIndex=" + nameIndex +
                ", signatureIndex=" + signatureIndex +
                ", tag=" + tag +
                ", name=" + name +
                '}';
    }
}
