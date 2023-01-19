package org.example.bytecode.parse;

import org.example.bytecode.parse.attribute.AttributeInfoParse;
import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;

import java.util.ArrayList;
import java.util.List;

public class FieldParse implements Parse {
    public int fieldCount;

    List<FieldItemParse> fieldItemParses = new ArrayList<>();
    private ConstantParse constantParse;

    public FieldParse(ConstantParse constantParse) {
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] bytes) {
        fieldCount = Utils.getU2Int(start, bytes);
        start += 2;
        for (int i = 0; i < fieldCount; i++) {
            FieldItemParse fieldItemParse = new FieldItemParse(constantParse);
            start = fieldItemParse.parse(start, bytes);
            fieldItemParses.add(fieldItemParse);
        }

        return start;
    }

    @Override
    public String toString() {
        return "FieldParse{" +
                "fieldCount=" + fieldCount +
                ", fieldItemParses=" + Utils.getListToString(fieldItemParses) +
                '}';
    }

    public static class FieldItemParse implements Parse {
        public AccessFlagParse accessFlag;
        public int nameIndex;
        public int descriptorIndex;
        public int attributeCount;

        public List<AttributeInfoParse> attributeInfoPars = new ArrayList<>();

        private ConstantParse constantParse;

        public FieldItemParse(ConstantParse constantParse) {
            this.constantParse = constantParse;
        }

        @Override
        public int parse(int start, byte[] bytes) {
            accessFlag = new AccessFlagParse();
            start = accessFlag.parse(start, bytes);
            nameIndex = Utils.getU2Int(start, bytes);
            start += 2;
            descriptorIndex = Utils.getU2Int(start, bytes);
            start += 2;
            attributeCount = Utils.getU2Int(start, bytes);
            start += 2;
            for (int j = 0; j < attributeCount; j++) {
                AttributeInfoParse attributeInfoParse = new AttributeInfoParse(constantParse);
                start = attributeInfoParse.parse(start, bytes);
                attributeInfoPars.add(attributeInfoParse);
            }
            return start;
        }

        @Override
        public String toString() {
            return "FieldItemParse{" +
                    "accessFlag=" + accessFlag +
                    ", nameIndex=" + nameIndex +
                    ", descriptorIndex=" + descriptorIndex +
                    ", attributeCount=" + attributeCount +
                    ", attributeInfoPars=" + Utils.getListToString(attributeInfoPars) +
                    '}';
        }
    }
}
