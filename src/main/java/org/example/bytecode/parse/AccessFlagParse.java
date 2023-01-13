package org.example.bytecode.parse;

import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;

public class AccessFlagParse implements Parse {
    public int accessFlag;

    @Override
    public int parse(int start, byte[] bytes) {
        accessFlag = Utils.getU2Int(start, bytes);
        start += 2;
        return start;
    }
}
