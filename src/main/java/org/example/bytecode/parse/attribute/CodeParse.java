package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.AttributeParse;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Parse;

import java.util.ArrayList;
import java.util.List;

public class CodeParse extends AttributeFormatParse {
    public int maxStack;
    public int maxLocals;
    public int codeLength;

    public int exceptionTableLength;

    public AttributeParse attributeParse;


    private static boolean wide = false;
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
        try {
            while (codeIndex < l) {
                int cmd = Utils.getU1Int(codeIndex, code);
                if (cmd > 201) {
                    throw new RuntimeException("error");
                }
                String cmdStr = Utils.getCMD(cmd);
                System.out.print("        " + (codeIndex - index) + ": " + cmdStr);
                codeIndex += 1;


                int default_offset = 0, low, high, npairs;
                int no_pad_bytes = 0, offset;
                int[] match, jump_table;

                if ((cmd == Utils.TABLESWITCH) || (cmd == Utils.LOOKUPSWITCH)) {
                    int remainder = (codeIndex - index) % 4;
                    no_pad_bytes = (remainder == 0) ? 0 : 4 - remainder;

                    for (int i = 0; i < no_pad_bytes; i++) {
                        int b = Utils.getU1Int(codeIndex, code);
                        codeIndex += 1;
                        if (b != 0) {

                        }
                    }

                    // Both cases have a field default_offset in common
                    default_offset = Utils.getU4Int(codeIndex, code);
                    codeIndex += 4;
                }

                switch (cmd) {

                    case Utils.TABLESWITCH: {
                        low = Utils.getU4Int(codeIndex, code);
                        codeIndex += 4;
                        high = Utils.getU4Int(codeIndex, code);
                        codeIndex += 4;

                        offset = (codeIndex - index) - 12 - no_pad_bytes - 1;
                        default_offset += offset;
                    }
                    break;

                    case Utils.LOOKUPSWITCH: {

                        npairs = Utils.getU4Int(codeIndex, code);
                        codeIndex += 4;
                        offset = (codeIndex - index) - 8 - no_pad_bytes - 1;

                        match = new int[npairs];
                        jump_table = new int[npairs];
                        default_offset += offset;

                        for (int i = 0; i < npairs; i++) {
                            match[i] = Utils.getU4Int(codeIndex, code);
                            codeIndex += 4;

                            jump_table[i] = offset + Utils.getU4Int(codeIndex, code);

                            codeIndex += 4;

                        }
                    }
                    break;


                    case Utils.GOTO:
                    case Utils.IFEQ:
                    case Utils.IFGE:
                    case Utils.IFGT:
                    case Utils.IFLE:
                    case Utils.IFLT:
                    case Utils.JSR:
                    case Utils.IFNE:
                    case Utils.IFNONNULL:
                    case Utils.IFNULL:
                    case Utils.IF_ACMPEQ:
                    case Utils.IF_ACMPNE:
                    case Utils.IF_ICMPEQ:
                    case Utils.IF_ICMPGE:
                    case Utils.IF_ICMPGT:
                    case Utils.IF_ICMPLE:
                    case Utils.IF_ICMPLT:
                    case Utils.IF_ICMPNE: {
                        int u2Int = Utils.getU2Int(codeIndex, code);
                        codeIndex += 2;
                        System.out.print(" " + "#" + (codeIndex - index - 1 + u2Int));
                    }
                    break;

                    case Utils.GOTO_W:
                    case Utils.JSR_W: {
                        int u4Int = Utils.getU4Int(codeIndex, code);
                        codeIndex += 4;
                        System.out.print(" " + "#" + (codeIndex - index - 1 + u4Int));
                    }
                    break;

                    case Utils.NEWARRAY: {
                        int u1Int = Utils.getU1Int(codeIndex, code);
                        codeIndex += 1;
                        System.out.print(" " + "<" + Utils.TYPE_NAMES[u1Int] + ">");
                    }
                    break;


                    case Utils.GETFIELD:
                    case Utils.GETSTATIC:
                    case Utils.PUTFIELD:
                    case Utils.PUTSTATIC:
                    case Utils.INSTANCEOF:
                    case Utils.INVOKESPECIAL:
                    case Utils.INVOKESTATIC:
                    case Utils.INVOKEVIRTUAL:
                    case Utils.INVOKEINTERFACE:
                    case Utils.LDC_W:
                    case Utils.LDC2_W:
                    case Utils.ANEWARRAY: {
                        int u2Int = Utils.getU2Int(codeIndex, code);
                        codeIndex += 2;
                        Object utfConstant = constantParse.getUtfConstant(u2Int);
                        System.out.print(" " + String.valueOf(utfConstant));

                    }
                    break;
                    case Utils.LDC: {
                        int u1Int = Utils.getU1Int(codeIndex, code);
                        codeIndex += 1;
                        Object utfConstant1 = constantParse.getUtfConstant(u1Int);
                        System.out.print(" " + String.valueOf(utfConstant1));
                    }
                    break;
                    case Utils.MULTIANEWARRAY: {
                        int u2Int = Utils.getU2Int(codeIndex, code);
                        codeIndex += 2;
                        Object utfConstant = constantParse.getUtfConstant(u2Int);
                        System.out.print(" " + String.valueOf(utfConstant));
                        int u1Int = Utils.getU1Int(codeIndex + 2, code);
                        codeIndex += 1;
                        System.out.print(" " + u1Int);
                    }
                    break;

                    case Utils.IINC: {
                        int u1Int = Utils.getU1Int(codeIndex, code);
                        codeIndex += 1;
                        System.out.print(" " + u1Int);
                        int u1Int2 = Utils.getU1Int(codeIndex + 1, code);
                        codeIndex += 1;
                        System.out.print(" " + u1Int2);
                    }
                    break;

                    default:
                        int paramsLength = Utils.getParamsLength(cmd);
                        if (paramsLength > 0) {
                            short[] paramType = Utils.getParamType(cmd);
                            for (short i : paramType) {
                                if (i == Utils.T_BYTE) {
                                    int u1Int = Utils.getU1Int(codeIndex, code);
                                    codeIndex += 1;
                                    System.out.print(" " + u1Int);
                                } else if (i == Utils.T_SHORT) {
                                    int u2Int = Utils.getU2Int(codeIndex, code);
                                    codeIndex += 2;
                                    System.out.print(" " + u2Int);
                                } else if (i == Utils.T_INT) {
                                    int u4Int = Utils.getU4Int(codeIndex, code);
                                    codeIndex += 4;
                                    System.out.print(" " + u4Int);
                                }
                            }
                            codeIndex += paramsLength;
                        }
                }

                System.out.print("\n");
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

            attributeParse = new AttributeParse(constantParse);
            attributeParse.parse(index, code);
        } catch (Exception e) {
            e.printStackTrace();
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
