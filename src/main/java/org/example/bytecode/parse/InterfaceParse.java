package org.example.bytecode.parse;

import org.example.bytecode.Parse;
import org.example.bytecode.Utils;

import java.util.ArrayList;
import java.util.List;

public class InterfaceParse implements Parse {
    public int interfacesCount;
    public List<InterfaceInfoParse> interfaceInfoParses = new ArrayList<>();

    @Override
    public int parse(int start, byte[] bytes) {
        interfacesCount = Utils.getU2Int(start, bytes);
        start += 2;

        for (int i = 0; i < interfacesCount; i++) {
            InterfaceInfoParse interfaceInfoParse = new InterfaceInfoParse();
            start = interfaceInfoParse.parse(start, bytes);
            interfaceInfoParses.add(interfaceInfoParse);
        }

        return start;
    }

    public static class InterfaceInfoParse implements Parse {
        public int index;

        @Override
        public int parse(int start, byte[] bytes) {
            index = Utils.getU2Int(start, bytes);
            start += 2;
            return start;
        }
    }
}
