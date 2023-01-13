package org.example.bytecode.parse;

import org.example.bytecode.parse.constant.Parse;

public class MagicParse implements Parse { //u4

    public String magic;

    @Override
    public int parse(int start, byte[] bytes) {
        int magicLength = 4;
        byte[] magicCode = new byte[magicLength];
        System.arraycopy(bytes, start, magicCode, 0, magicLength);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : magicCode) {
            int value = Byte.toUnsignedInt(b);
            stringBuilder.append(Integer.toHexString(value).toUpperCase());
        }

        if (!"CAFEBABE".equals(stringBuilder.toString())) {
            throw new RuntimeException("class error");
        }

        magic = stringBuilder.toString();

        return start + magicLength;
    }

    @Override
    public String toString() {
        return "MagicParse{" +
                "magic='" + magic + '\'' +
                '}';
    }
}
