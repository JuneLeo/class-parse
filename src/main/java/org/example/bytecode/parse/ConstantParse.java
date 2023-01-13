package org.example.bytecode.parse;

import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;
import org.example.bytecode.parse.constant.*;

import java.util.ArrayList;
import java.util.List;

public class ConstantParse implements Parse {
    public int constantPoolCount;


    private List<Parse> constantParse = new ArrayList<>();


    @Override
    public int parse(int start, byte[] bytes) {

        constantPoolCount = Utils.getU2Int(start, bytes);

        // 常量池中的数量= constantPoolCount-1
        start += 2;
        for (int i = 0; i < constantPoolCount - 1; i++) {
            int tag = Utils.getU1Int(start, bytes);
            start += 1;
            ConstantInfoParse p = getConstantParse(tag);
            p.setIndex(i + 1);
            constantParse.add(p);
            start = p.parse(start, bytes);
        }

        return start;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Parse parse : constantParse) {
            String s = parse.toString();
            stringBuilder.append(s);
            stringBuilder.append(",");
        }

        return "ConstantPoolParse{" +
                "constantPoolCount=" + constantPoolCount +
                ", constantParse=" + stringBuilder +
                '}';
    }

    public ConstantInfoParse getConstantParse(int tag) {
        switch (tag) {
            case 1:
                return new ConstantUtf8InfoParse(tag, "CONSTANT_Utf8_info");
            case 3:
                return new ConstantIntegerInfoParse(tag, "CONSTANT_Integer_info");
            case 4:
                return new ConstantFloatInfoParse(tag, "CONSTANT_Float_info");
            case 5:
                return new ConstantLongInfoParse(tag, "CONSTANT_Long_info");
            case 6:
                return new ConstantDoubleInfoParse(tag, "CONSTANT_Double_info");
            case 7:
                return new ConstantClassInfoParse(tag, "CONSTANT_Class_info");
            case 8:
                return new ConstantStringInfoParse(tag, "CONSTANT_String_info");
            case 9:
                return new ConstantFieldRefInfoParse(tag, "CONSTANT_Fieldref_info");
            case 10:
                return new ConstantMethodRefInfoParse(tag, "CONSTANT_Methodref_info");
            case 11:
                return new ConstantInterfaceInfoParse(tag, "CONSTANT_Interface-Methodref_info");
            case 12:
                return new ConstantNameAndTypeInfoParse(tag, "CONSTANT_NameAndType_info");
            case 15:
                return new ConstantMethodHandleInfoParse(tag, "CONSTANT_Method-Handle_info");
            case 16:
                return new ConstantMethodTypeInfoParse(tag, "CONSTANT_Method-type_info");
            case 18:
                return new ConstantInvokeDynamicInfoParse(tag, "CONSTANT_Invoke-Dynamic_info");
        }
        return null;
    }


}
