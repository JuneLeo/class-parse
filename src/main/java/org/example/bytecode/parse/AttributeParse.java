package org.example.bytecode.parse;

import org.example.bytecode.parse.attribute.AttributeInfoParse;
import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;

import java.util.ArrayList;
import java.util.List;

public class AttributeParse implements Parse {
    public int attributeCount;

    public List<AttributeInfoParse> attributeInfoPars = new ArrayList<>();

    private ConstantParse constantParse;
    public AttributeParse(ConstantParse constantParse) {
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] bytes) {
        attributeCount = Utils.getU2Int(start, bytes);
        start += 2;
        for (int i = 0; i < attributeCount; i++) {
            AttributeInfoParse attributeInfoParse = new AttributeInfoParse(constantParse);
            start = attributeInfoParse.parse(start, bytes);
            attributeInfoPars.add(attributeInfoParse);
        }
        return start;
    }


    @Override
    public String toString() {
        return "AttributeParse{" +
                "attributeCount=" + attributeCount +
                ", attributeInfoPars=" + Utils.getListToString(attributeInfoPars) +
                '}';
    }
}
