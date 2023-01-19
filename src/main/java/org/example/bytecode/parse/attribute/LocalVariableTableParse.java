package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Parse;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTableParse extends AttributeFormatParse {

    private ConstantParse constantParse;
    public List<LocalVariableTableInnerParse> localVariableTableInnerParses = new ArrayList<>();

    public LocalVariableTableParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        int length = Utils.getU2Int(start, code);
        start += 2;
        System.out.println("LocalVariableTable:");
        for (int i = 0; i < length; i++) {
            LocalVariableTableInnerParse innerParse = new LocalVariableTableInnerParse(constantParse);
            start = innerParse.parse(start, code);
            System.out.println(innerParse);
            localVariableTableInnerParses.add(innerParse);
        }

        return start;
    }


    private static class LocalVariableTableInnerParse implements Parse {

        private ConstantParse constantParse;


        public int startPc;
        public int length;
        public int nameIndex;
        public int descriptorIndex;
        public int index;

        public LocalVariableTableInnerParse(ConstantParse constantParse) {
            this.constantParse = constantParse;
        }

        /**
         * LocalVariableTable:
         * Start  Length  Slot  Name   Signature
         * 33       4     2     e   Ljava/lang/RuntimeException;
         * 8      51     1 person   Lorg/example/Person;
         * 63       4     1     e   Ljava/lang/Exception;
         * 0      68     0  args   [Ljava/lang/String;
         *
         * @param start
         * @param code
         * @return
         */
        @Override
        public int parse(int start, byte[] code) {
            startPc = Utils.getU2Int(start, code);
            start += 2;
            length = Utils.getU2Int(start, code);
            start += 2;
            nameIndex = Utils.getU2Int(start, code);
            start += 2;
            descriptorIndex = Utils.getU2Int(start, code);
            start += 2;
            index = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }


        @Override
        public String toString() {
            return "Start=" + startPc +
                    ", Length=" + length +
                    ", Slot=" + index +
                    ", nameIndex=" + getUtf(nameIndex) +
                    ", descriptorIndex=" + getUtf(descriptorIndex);

        }


        private String getUtf(int catchType) {
            return String.valueOf(constantParse.getUtfConstant(catchType));
        }
    }
}
