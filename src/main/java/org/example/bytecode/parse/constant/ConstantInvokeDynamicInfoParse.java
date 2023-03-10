package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

public class ConstantInvokeDynamicInfoParse extends ConstantInfoParse implements Parse {
    public int bootstrapMethodAttrIndex;
    public int nameAndTypeIndex;

    public ConstantInvokeDynamicInfoParse(int tag, String name) {
        super(tag, name);
    }


    @Override
    public int parse(int start, byte[] bytes) {
        bootstrapMethodAttrIndex = Utils.getU2Int(start, bytes);
        start += 2;
        nameAndTypeIndex = Utils.getU2Int(start, bytes);
        start += 2;
        return start;
    }

    @Override
    public String toString() {
        return "[ " +"name=" + name +
                ", bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex+ " ]";
    }
}
