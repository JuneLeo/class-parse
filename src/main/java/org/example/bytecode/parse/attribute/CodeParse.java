package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Parse;

import java.util.ArrayList;
import java.util.List;

public class CodeParse extends AttributeFormatParse {
    public int attributeNameIndex;
    public int attributeLength;
    public int maxStack;
    public int maxLocals;
    public int codeLength;

    public int exceptionTableLength;

    public int attributeCount;

    public List<AttributeInfoParse> attributeInfoPars = new ArrayList<>();

    public List<ExceptionInfoParse> exceptionInfoParses = new ArrayList<>();


    private ConstantParse constantParse;

    public CodeParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        System.out.println("Code:");
        int index = start;
        maxStack = Utils.getU2Int(index, code);//u2
        index += 2;
        maxLocals = Utils.getU2Int(index, code); //u2
        index += 2;
        System.out.println("    maxStack=" + maxStack + "  maxLocals=" + maxLocals);
        codeLength = Utils.getU4Int(index, code); //u4
        index += 4;

        int l = index + codeLength;
        int codeIndex = index;
        while (codeIndex < l) {
            int cmd = Utils.getU1Int(codeIndex, code);
            String cmdStr = Utils.getCMD(cmd);
            System.out.print("        " + (codeIndex - index) + ": " + cmdStr);
            codeIndex += 1;
            int paramsLength = Utils.getParamsLength(cmd);
            if (paramsLength >= 0) {
                if (paramsLength == 1) {
                    int u1Int = Utils.getU1Int(codeIndex, code);
                    Object utfConstant = constantParse.getUtfConstant(u1Int);
                    System.out.print(" " + String.valueOf(utfConstant));
                } else if (paramsLength == 2) {
                    int u2Int = Utils.getU2Int(codeIndex, code);
                    Object utfConstant = constantParse.getUtfConstant(u2Int);
                    System.out.print(" " + String.valueOf(utfConstant));
                } else {
                    for (int i = 0; i < paramsLength; i++) {
                        System.out.print(" " + Utils.getU1Int(codeIndex + i, code));
                    }
                }
                codeIndex += paramsLength;
                System.out.print("\n");
            } else {
                System.out.print("\n");
                break;
            }
        }
        index += codeLength;

        exceptionTableLength = Utils.getU2Int(index, code);
        index += 2;

        if (exceptionTableLength > 0) {
            System.out.println("Exception table:");
            for (int i = 0; i < exceptionTableLength; i++) {
                ExceptionInfoParse exceptionInfoParse = new ExceptionInfoParse(constantParse);
                index = exceptionInfoParse.parse(index, code);
                System.out.println("    " + exceptionInfoParse);
                exceptionInfoParses.add(exceptionInfoParse);
            }
        }

        attributeCount = Utils.getU2Int(index, code);
        index += 2;
        for (int j = 0; j < attributeCount; j++) {
            AttributeInfoParse attributeInfoParse = new AttributeInfoParse(constantParse);
            index = attributeInfoParse.parse(index, code);
            attributeInfoPars.add(attributeInfoParse);
        }

        return start + length;
    }


    public static class ExceptionInfoParse implements Parse {
        public int startPc;
        public int endPc;
        public int handlerPc;
        public int catchType;

        private ConstantParse constantParse;

        public ExceptionInfoParse(ConstantParse constantParse) {

            this.constantParse = constantParse;
        }

        @Override
        public int parse(int start, byte[] code) {
            /**
             *             from    to  target type
             *             12    21    32   Class java/lang/RuntimeException
             *             12    21    48   any
             *             32    37    48   any
             *              0    59    62   Class java/lang/Exception
             */
            startPc = Utils.getU2Int(start, code);
            start += 2;
            endPc = Utils.getU2Int(start, code);
            start += 2;
            handlerPc = Utils.getU2Int(start, code);
            start += 2;
            catchType = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }

        @Override
        public String toString() {
            return
                    "from=" + startPc +
                            ", to=" + endPc +
                            ", target=" + handlerPc +
                            ", type=" + getCatchType(catchType);
        }

        private String getCatchType(int catchType) {
            return catchType == 0 ? "any" : String.valueOf(constantParse.getUtfConstant(catchType));
        }
    }
}
