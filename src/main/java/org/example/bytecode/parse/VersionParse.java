package org.example.bytecode.parse;

import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;

public class VersionParse implements Parse {
    public int minor; // u2
    public int major; // u2

    @Override
    public int parse(int start, byte[] bytes) {
        minor = Utils.getU2Int(start, bytes);
        start += 2;
        major = Utils.getU2Int(start, bytes);

        return start + 2;
    }

    @Override
    public String toString() {
        return "VersionParse{" +
                "minor=" + minor +
                ", major=" + major +
                '}';
    }
}
