package org.example.bytecode.parse;

import org.example.bytecode.AccessFlag;
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
        for (int i = 0; i < accessFlags.size(); i++) {
            stringBuilder.append(accessFlags.get(i));
            if (i != accessFlags.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
