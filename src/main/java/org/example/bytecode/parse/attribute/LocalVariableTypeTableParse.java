package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Parse;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTypeTableParse extends AttributeFormatParse {
    private ConstantParse constantParse;

    public int localVariableTypeTableLength;

    public List<LocalVariableTypeInnerParse> localVariableTypeInnerParseList = new ArrayList<>();

    public LocalVariableTypeTableParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        localVariableTypeTableLength = Utils.getU2Int(start, code);
        start += 2;
        System.out.println("LocalVariableTypeTable:");
        for (int i = 0; i < localVariableTypeTableLength; i++) {
            LocalVariableTypeInnerParse innerParse = new LocalVariableTypeInnerParse(constantParse);
            start = innerParse.parse(start, code);
            System.out.println(innerParse);
            localVariableTypeInnerParseList.add(innerParse);
        }

        return start;
    }


    private static class LocalVariableTypeInnerParse implements Parse {
        public int startPc;        // Range in which the variable is valid
        public int length;
        public int nameIndex;      // Index in constant pool of variable name
        public int signatureIndex; // Index of variable signature
        public int index;

        private ConstantParse constantParse;

        public LocalVariableTypeInnerParse(ConstantParse constantParse) {
            this.constantParse = constantParse;
        }

        @Override
        public int parse(int start, byte[] code) {
            startPc = Utils.getU2Int(start, code);
            start += 2;
            length = Utils.getU2Int(start, code);
            start += 2;
            nameIndex = Utils.getU2Int(start, code);
            start += 2;

            signatureIndex = Utils.getU2Int(start, code);
            start += 2;

            index = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }
        // LocalVariableTypeTable:
        //        Start  Length  Slot  Name   Signature
        //            0      10     0     a   Ljava/util/List<Ljava/lang/Integer;>;
        @Override
        public String toString() {
            return "    " +
                    "Start=" + startPc +
                    ", Length=" + length +
                    ", Slot=" + index +
                    ", Name=" + Utils.getUtf(nameIndex, constantParse) +
                    ", Signature=" + Utils.getUtf(signatureIndex, constantParse);

        }
    }
}
