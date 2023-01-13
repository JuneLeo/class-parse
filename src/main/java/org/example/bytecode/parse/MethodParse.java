package org.example.bytecode.parse;

import org.example.bytecode.Parse;
import org.example.bytecode.Utils;

import java.util.ArrayList;
import java.util.List;

public class MethodParse implements Parse {
    public int methodCount;
    public AccessFlagParse accessFlag;

    public int nameIndex;

    public int descriptorIndex;

    public int attributeCount;

    public List<AttributeInfoParse> attributeInfoPars = new ArrayList<>();

    @Override
    public int parse(int start, byte[] bytes) {
        methodCount = Utils.getU2Int(start, bytes);
        start += 2;
        for (int i = 0; i < methodCount; i++) {
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
        }

        return start;
    }
}
