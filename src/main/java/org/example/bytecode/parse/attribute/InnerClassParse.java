package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.AccessFlagParse;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Parse;

import java.util.ArrayList;
import java.util.List;

public class InnerClassParse extends AttributeFormatParse {
    private ConstantParse constantParse;
    public int numberOfClass;
    List<InnerClassInfoParse> innerClassInfoParseList = new ArrayList<>();

    public InnerClassParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;

    }

    @Override
    public int parse(int start, byte[] code) {
        System.out.println("InnerClasses:");
        numberOfClass = Utils.getU2Int(start, code);
        start += 2;
        for (int i = 0; i < numberOfClass; i++) {
            System.out.print("    ");
            InnerClassInfoParse infoParse = new InnerClassInfoParse(constantParse);
            start = infoParse.parse(start, code);
            System.out.println("    " +infoParse);
            innerClassInfoParseList.add(infoParse);
        }
        return start;
    }

    //InnerClasses:
    //     public static #68= #67 of #43; //Callback=class org/example/Person$Callback of class org/example/Person
    //     public static final #87= #86 of #89; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < innerClassInfoParseList.size(); i++) {
            stringBuilder.append("    " + innerClassInfoParseList.get(i));
            if (i != innerClassInfoParseList.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private static class InnerClassInfoParse implements Parse {
        private ConstantParse constantParse;

        public int innerClassInfoIndex;
        public int outClassInfoIndex;
        public int innerNameIndex;
        public AccessFlagParse accessFlagParse;


        public InnerClassInfoParse(ConstantParse constantParse) {
            this.constantParse = constantParse;
        }

        @Override
        public int parse(int start, byte[] code) {
            innerClassInfoIndex = Utils.getU2Int(start, code);
            start += 2;
            outClassInfoIndex = Utils.getU2Int(start, code);
            start += 2;
            innerNameIndex = Utils.getU2Int(start, code);
            start += 2;
            accessFlagParse = new AccessFlagParse();
            start = accessFlagParse.parse(start, code);
            return start;
        }

        @Override
        public String toString() {
            return

                    "innerClassInfoIndex=" + Utils.getUtf(innerClassInfoIndex, constantParse) +
                            ", outClassInfoIndex=" + Utils.getUtf(outClassInfoIndex, constantParse) +
                            ", innerNameIndex=" + Utils.getUtf(innerNameIndex, constantParse) +
                            ", accessFlagParse=" + accessFlagParse;
        }
    }
}
