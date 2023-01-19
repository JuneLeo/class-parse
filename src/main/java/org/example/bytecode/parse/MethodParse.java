package org.example.bytecode.parse;

import org.example.bytecode.parse.attribute.AttributeInfoParse;
import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;

import java.util.ArrayList;
import java.util.List;

public class MethodParse implements Parse {
    public int methodCount;

    public List<MethodItemParse> methodItemParses = new ArrayList<>();

    private ConstantParse constantParse;

    public MethodParse(ConstantParse constantParse) {
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] bytes) {
        methodCount = Utils.getU2Int(start, bytes);
        start += 2;
        for (int i = 0; i < methodCount; i++) {
            MethodItemParse methodItemParse = new MethodItemParse(constantParse);
            start = methodItemParse.parse(start, bytes);
            methodItemParses.add(methodItemParse);
        }

        return start;
    }

    @Override
    public String toString() {
        return "MethodParse{" +
                "methodCount=" + methodCount +
                ", methodItemParses=" + Utils.getListToString(methodItemParses) +
                '}';
    }

    public static class MethodItemParse implements Parse {
        public AccessFlagParse accessFlag;
        public int nameIndex;
        public int descriptorIndex;
        public int attributeCount;

        public List<AttributeInfoParse> attributeInfoPars = new ArrayList<>();

        private ConstantParse constantParse;

        public MethodItemParse(ConstantParse constantParse) {
            this.constantParse = constantParse;
        }

        /**
         * public org.example.Main();
         * descriptor: ()V
         * flags: ACC_PUBLIC
         *
         * @param start
         * @param bytes
         * @return
         */
        @Override
        public int parse(int start, byte[] bytes) {
            accessFlag = new AccessFlagParse();
            start = accessFlag.parse(start, bytes);
            nameIndex = Utils.getU2Int(start, bytes);
            System.out.println("method: " + constantParse.getUtfConstant(nameIndex));
            start += 2;
            descriptorIndex = Utils.getU2Int(start, bytes);
            System.out.println("descriptor: " + constantParse.getUtfConstant(descriptorIndex));
            System.out.println("flags: " + accessFlag);
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
            return "MethodItemParse{" +
                    "accessFlag=" + accessFlag +
                    ", nameIndex=" + nameIndex +
                    ", descriptorIndex=" + descriptorIndex +
                    ", attributeCount=" + attributeCount +
                    ", attributeInfoPars=" + attributeInfoPars +
                    '}';
        }
    }
}
