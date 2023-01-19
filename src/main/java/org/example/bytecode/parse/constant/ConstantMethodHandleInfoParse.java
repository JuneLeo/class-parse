package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

public class ConstantMethodHandleInfoParse extends ConstantInfoParse implements Parse {
    public int referenceKind;
    public int referenceIndex;

    public ConstantMethodHandleInfoParse(int tag, String name) {
        super(tag, name);
    }


    @Override
    public int parse(int start, byte[] bytes) {
        referenceKind = Utils.getU1Int(start, bytes);
        start += 1;
        referenceIndex = Utils.getU2Int(start, bytes);
        start += 2;
        return start;
    }

    @Override
    public String toString() {
        return "ConstantMethodHandleInfoParse{" +
                "referenceKind=" + referenceKind +
                ", referenceIndex=" + referenceIndex +
                ", tag=" + tag +
                ", name=" + name +
                '}';
    }
}
