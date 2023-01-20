package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

public class ConstantMethodRefInfoParse extends ConstantInfoParse implements Parse {
    public int classIndex;
    public int nameAndTypeIndex;

    public ConstantMethodRefInfoParse(int tag, String name) {
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
        return "[ " +"name=" + name +
                ", classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex+ " ]";
    }
}
