package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Parse;

import java.util.ArrayList;
import java.util.List;

public class ExceptionsParse extends AttributeFormatParse {
    private ConstantParse constantParse;

    public List<ExceptionsInfoParse> exceptionsInfoParses = new ArrayList<>();

    public ExceptionsParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        int numberOfExceptions = Utils.getU2Int(start, code);
        start += 2;
        System.out.println("Exceptions:");
        for (int i = 0; i < numberOfExceptions; i++) {
            ExceptionsInfoParse exceptionsInfoParse = new ExceptionsInfoParse(constantParse);
            start = exceptionsInfoParse.parse(start, code);
            System.out.println(exceptionsInfoParse);
            exceptionsInfoParses.add(exceptionsInfoParse);
        }


        return start;
    }

    private static class ExceptionsInfoParse implements Parse {
        private ConstantParse constantParse;
        public int exceptionIndex;

        public ExceptionsInfoParse(ConstantParse constantParse) {
            this.constantParse = constantParse;
        }

        @Override
        public int parse(int start, byte[] code) {
            exceptionIndex = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }

        @Override
        public String toString() {
            return Utils.getUtf(exceptionIndex, constantParse);
        }
    }


}
