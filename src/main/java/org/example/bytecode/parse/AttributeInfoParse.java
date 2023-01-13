package org.example.bytecode.parse;

import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;

import java.util.ArrayList;
import java.util.List;

public class AttributeInfoParse implements Parse {
    public int nameIndex;
    public int attributeLength;

    public List<Info> infos = new ArrayList<>();

    @Override
    public int parse(int start, byte[] bytes) {
        nameIndex = Utils.getU2Int(start, bytes);
        start += 2;
        attributeLength = Utils.getU4Int(start, bytes);
        start += 4;

        for (int i = 0; i < attributeLength; i++) {
            Info info = new Info();
            start = info.parse(start, bytes);
            infos.add(info);
        }

        return start;
    }

    @Override
    public String toString() {
        return "AttributeInfoParse{" +
                "nameIndex=" + nameIndex +
                ", attributeLength=" + attributeLength +
                ", infos=" + Utils.getListToString(infos) +
                '}';
    }

    public static class Info implements Parse {
        public int value;

        @Override
        public int parse(int start, byte[] bytes) {
            value = Utils.getU1Int(start, bytes);
            return start + 1;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "value=" + value +
                    '}';
        }
    }

}
