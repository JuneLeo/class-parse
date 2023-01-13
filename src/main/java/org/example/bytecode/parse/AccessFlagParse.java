package org.example.bytecode.parse;

import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;

import java.util.ArrayList;
import java.util.List;

public class AccessFlagParse implements Parse {
    public int value;
    public List<String> accessFlags = new ArrayList<>();

    @Override
    public int parse(int start, byte[] bytes) {
        value = Utils.getU2Int(start, bytes);
        format();
        start += 2;
        return start;
    }

    private void format() {
        for (AccessFlag access : AccessFlag.values()) {
            if ((access.value & value) > 0) {
                accessFlags.add(access.name());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String accessFlag : accessFlags) {
            stringBuilder.append(accessFlag);
            stringBuilder.append(",");
        }
        return "AccessFlagParse{" +
                "value=" + value +
                ", accessFlags=" + stringBuilder +
                '}';
    }
}
