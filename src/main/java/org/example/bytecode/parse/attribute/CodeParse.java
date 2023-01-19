package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.ConstantInfoParse;

public class CodeParse extends AttributeFormatParse {
    public int attributeNameIndex;
    public int attributeLength;
    public int maxStack;
    public int maxLocals;
    public int codeLength;

    public int exceptionTableLength;

    public int attributesCount;


    private ConstantParse constantParse;

    public CodeParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        int index = start;
        maxStack = Utils.getU2Int(index, code);//u2
        index += 2;
        maxLocals = Utils.getU2Int(index, code); //u2
        index += 2;
        codeLength = Utils.getU4Int(index, code); //u4
        index += 4;

        int l = index + codeLength;
        while (index < l) {
            int cmd = Utils.getU1Int(index, code);
            index += 1;
            String cmdStr = Utils.getCMD(cmd);
            System.out.print(cmdStr);
            int paramsLength = Utils.getParamsLength(cmd);
            if (paramsLength >= 0) {
                if (paramsLength == 1) {
                    int u1Int = Utils.getU1Int(index, code);
                    Object utfConstant = constantParse.getUtfConstant(u1Int);
                    System.out.print(" " + String.valueOf(utfConstant));
                } else if (paramsLength == 2) {
                    int u2Int = Utils.getU2Int(index, code);
                    Object utfConstant = constantParse.getUtfConstant(u2Int);
                    System.out.print(" " + String.valueOf(utfConstant));
                } else {
                    for (int i = 0; i < paramsLength; i++) {
                        System.out.print(" " + Utils.getU1Int(index + i, code));
                    }
                }
                index += paramsLength;
            } else {
                break;
            }
            System.out.print("\n");
        }
        index += codeLength;


//        exceptionTableLength = Utils.getU2Int(index, code);
//        index += 2;
//        // todo exception info
//        attributeLength = Utils.getU2Int(index, code);
//        index += 2;
//        // todo attribute


        return start + length;
    }
}
