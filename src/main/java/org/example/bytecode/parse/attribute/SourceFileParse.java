package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;

public class SourceFileParse extends AttributeFormatParse {
    private ConstantParse constantParse;
    public int sourceFileIndex;

    public SourceFileParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        sourceFileIndex = Utils.getU2Int(start, code);
        start += 2;
        System.out.println("SourceFile: ");
        System.out.println(constantParse.getUtfConstant(sourceFileIndex));
        return start;
    }
}
