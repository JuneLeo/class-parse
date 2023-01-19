package org.example.bytecode.parse.constant;

import org.example.bytecode.Utils;

import java.nio.charset.StandardCharsets;

public class ConstantUtf8InfoParse extends ConstantInfoParse implements Parse {
    int length;
    public String value;

    public ConstantUtf8InfoParse(int tag, String name) {
        super(tag, name);
    }

    @Override
    public int parse(int start, byte[] bytes)  {
        length = Utils.getU2Int(start, bytes);
        start += 2;
        byte[] strCode = new byte[length];
        System.arraycopy(bytes, start, strCode, 0, length);
        value = new String(strCode, StandardCharsets.UTF_8);
        start += length;
        return start;
    }

    @Override
    public String toString() {
        return "ConstantUtf8InfoParse{" +
                "length=" + length +
                ", value='" + value + '\'' +
                ", tag=" + tag +
                ", name=" + name +
                '}';
    }
}
