package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Index;
import org.example.bytecode.parse.constant.Parse;

import java.util.ArrayList;
import java.util.List;

public class LineNumberTableParse extends AttributeFormatParse {
    private ConstantParse constantParse;
    public List<LineNumberTableInnerParse> lineNumberTableInnerParses = new ArrayList<>();

    public LineNumberTableParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    /**
     * LineNumberTable:
     * line 6: 0
     * line 7: 8
     * line 9: 12
     * line 15: 21
     * line 16: 29
     * line 12: 32
     * line 13: 33
     * line 15: 37
     * line 16: 45
     * line 15: 48
     * line 16: 57
     * line 19: 59
     * line 17: 62
     * line 18: 63
     * line 20: 67
     *
     * @param start
     * @param code
     * @return
     */
    @Override
    public int parse(int start, byte[] code) {
        int length = Utils.getU2Int(start, code);
        start += 2;
        System.out.println("LineNumberTable:");
        for (int i = 0; i < length; i++) {
            LineNumberTableInnerParse innerParse = new LineNumberTableInnerParse();
            start = innerParse.parse(start, code);
            System.out.println(innerParse);
            lineNumberTableInnerParses.add(innerParse);
        }
        return start;
    }

    private static class LineNumberTableInnerParse implements Parse {
        public int startPc;
        public int lineNumber;

        @Override
        public int parse(int start, byte[] code) {
            startPc = Utils.getU2Int(start, code);
            start += 2;
            lineNumber = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }

        @Override
        public String toString() {
            return "line " + lineNumber +
                    "：" + startPc;
        }
    }
}
