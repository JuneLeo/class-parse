package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

public class ConstantFieldRefInfoParse extends ConstantInfoParse implements Parse {
    public int classIndex;
    public int nameAndTypeIndex;

    public ConstantFieldRefInfoParse(int tag, String name) {
        super(tag, name);
    }


    @Override
    public int parse(int start, byte[] bytes) {
        classIndex = Utils.getU2Int(start, bytes);
        start += 2;
        nameAndTypeIndex = Utils.getU2Int(start, bytes);
        start += 2;
        return start;
    }

    @Override
    public String toString() {
        return "ConstantFieldRefInfoParse{" +
                "classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                ", tag=" + tag +
                ", name=" + name +
                '}';
    }
}
