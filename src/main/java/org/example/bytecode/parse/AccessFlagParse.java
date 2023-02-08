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
        System.out.println("flags: " + this);
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

    private enum AccessFlag {
        ACC_PUBLIC(0x0001),     // Declared public; may be accessed from outside its package.
        ACC_PRIVATE(0x0002),     // Declared private; usable only within the defining class.
        ACC_PROTECTED(0x0004),     // Declared protected; may be accessed within subclasses.
        ACC_STATIC(0x0008),     // Declared static.
        ACC_FINAL(0x0010),     // Declared final; no subclasses allowed.
        ACC_SYNCHRONIZED(0x0020),     // Treat superclass methods specially when invoked by the invokespecial instruction.
        ACC_VOLATILE(0x0040),     // Declared volatile; cannot be cached.
        ACC_TRANSIENT(0x0080),     // Declared transient; not written or read by a persistent object manager.
        ACC_NATIVE(0x0100),     // Declared native; implemented in a language other than Java.
        ACC_INTERFACE(0x0200),     // Is an interface, not a class.
        ACC_ABSTRACT(0x0400),     // Declared abstract; may not be instantiated.
        ACC_STRICT(0x0800);


        public int value;

        AccessFlag(int value) {
            this.value = value;
        }
    }
}
