package org.example.bytecode.parse;

import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;

public class ClassExtParse implements Parse {

    public int thisClassIndex;
    public int superClassIndex;

    @Override
    public int parse(int start, byte[] bytes) {
        thisClassIndex = Utils.getU2Int(start, bytes);
        start += 2;
        superClassIndex = Utils.getU2Int(start, bytes);
        start += 2;

        return start;
    }

    @Override
    public String toString() {
        return "ClassExtParse{" +
                "thisClassIndex=" + thisClassIndex +
                ", superClassIndex=" + superClassIndex +
                '}';
    }
}
