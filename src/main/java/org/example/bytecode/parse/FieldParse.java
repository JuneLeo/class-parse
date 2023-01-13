package org.example.bytecode.parse;

import org.example.bytecode.Parse;
import org.example.bytecode.Utils;

import java.util.ArrayList;
import java.util.List;

public class FieldParse implements Parse {
    public int fieldCount;

    List<FieldItemParse> fieldItemParses = new ArrayList<>();

    @Override
    public int parse(int start, byte[] bytes) {
        fieldCount = Utils.getU2Int(start, bytes);
        start += 2;
        for (int i = 0; i < fieldCount; i++) {
            FieldItemParse fieldItemParse = new FieldItemParse();
            start = fieldItemParse.parse(start, bytes);
            fieldItemParses.add(fieldItemParse);
        }

        return start;
    }

    public static class FieldItemParse implements Parse {
        public AccessFlagParse accessFlag;
        public int nameIndex;
        public int descriptorIndex;
        public int attributeCount;

        public List<AttributeInfoParse> attributeInfoPars = new ArrayList<>();

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
                AttributeInfoParse attributeInfoParse = new AttributeInfoParse();
                start = attributeInfoParse.parse(start, bytes);
                attributeInfoPars.add(attributeInfoParse);
            }
            return start;
        }
    }
}
