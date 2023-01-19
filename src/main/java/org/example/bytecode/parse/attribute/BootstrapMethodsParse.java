package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Parse;

import java.util.ArrayList;
import java.util.List;

public class BootstrapMethodsParse extends AttributeFormatParse {
    private ConstantParse constantParse;
    public int numBootstrapMethods;

    public List<BootstrapMethodsInnerParse> bootstrapMethodsInnerParseList = new ArrayList<>();

    public BootstrapMethodsParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        numBootstrapMethods = Utils.getU2Int(start, code);
        start += 2;
        System.out.println("BootstrapMethods:");
        for (int i = 0; i < numBootstrapMethods; i++) {
            BootstrapMethodsInnerParse bootstrapMethodsInnerParse = new BootstrapMethodsInnerParse(constantParse);
            start = bootstrapMethodsInnerParse.parse(start, code);
            System.out.println("    " + bootstrapMethodsInnerParse);
            bootstrapMethodsInnerParseList.add(bootstrapMethodsInnerParse);
        }
        return start;
    }


    private static class BootstrapMethodsInnerParse implements Parse {
        private ConstantParse constantParse;

        public int bootstrapMethodRef;
        public int numBootstrapArguments;

        public List<BootstrapArgumentInnerParse> bootstrapArgumentInnerParses = new ArrayList<>();

        public BootstrapMethodsInnerParse(ConstantParse constantParse) {
            this.constantParse = constantParse;
        }

        @Override
        public int parse(int start, byte[] code) {
            bootstrapMethodRef = Utils.getU2Int(start, code);
            start += 2;
            numBootstrapArguments = Utils.getU2Int(start, code);
            start += 2;
            for (int i = 0; i < numBootstrapArguments; i++) {
                BootstrapArgumentInnerParse bootstrapArgumentInnerParse = new BootstrapArgumentInnerParse(constantParse);
                start = bootstrapArgumentInnerParse.parse(start, code);
                bootstrapArgumentInnerParses.add(bootstrapArgumentInnerParse);
            }
            return 0;
        }

        //BootstrapMethods:
        //  0: #46 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        //    Method arguments:
        //      #47 ()V
        //      #48 invokestatic org/example/Main.lambda$main$0:()V
        //      #47 ()V
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (BootstrapArgumentInnerParse bootstrapArgumentInnerPars : bootstrapArgumentInnerParses) {
                stringBuilder.append("\n");
                stringBuilder.append("            "+bootstrapArgumentInnerPars);
            }
            return Utils.getUtf(bootstrapMethodRef, constantParse) + "\n        Method arguments:" + stringBuilder.toString();
        }
    }

    private static class BootstrapArgumentInnerParse implements Parse {
        private ConstantParse constantParse;
        public int bootstrapArgument;

        public BootstrapArgumentInnerParse(ConstantParse constantParse) {
            this.constantParse = constantParse;
        }

        @Override
        public int parse(int start, byte[] code) {
            bootstrapArgument = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }

        @Override
        public String toString() {
            return Utils.getUtf(bootstrapArgument, constantParse);
        }
    }

}
