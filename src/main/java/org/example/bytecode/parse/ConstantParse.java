package org.example.bytecode.parse;

import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;
import org.example.bytecode.parse.constant.*;

import java.util.ArrayList;
import java.util.List;

public class ConstantParse implements Parse {
    public int constantPoolCount;


    private ConstantInfoParse[] constantParse;


    @Override
    public int parse(int start, byte[] bytes) {

        constantPoolCount = Utils.getU2Int(start, bytes);
        constantParse = new ConstantInfoParse[constantPoolCount];
        System.out.println("Constant pool: ");
        // 常量池中的数量= constantPoolCount-1
        start += 2;

        for (int i = 1; i < constantPoolCount; i++) {

            int tag = Utils.getU1Int(start, bytes);
            start += 1;
            ConstantInfoParse p = getConstantParse(tag);
            p.setIndex(i);
            start = p.parse(start, bytes);
            constantParse[i] = p;
            System.out.println("    #" + (i) + " = " + p);

            if (p instanceof ConstantLongInfoParse || p instanceof ConstantDoubleInfoParse) {
                i++;
            }
        }

        return start;
    }


    @Override
    public String toString() {

        return "ConstantPoolParse{" +
                "constantPoolCount=" + constantPoolCount +
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
            default:
                return null;
        }
    }

    public Object getUtfConstant(int nameIndex) {
        return getUtfConstant(nameIndex, true);
    }

    public Object getUtfConstant(int nameIndex, boolean realIndex) {
        ConstantInfoParse infoParse = constantParse[nameIndex];

        if (infoParse == null) {
//            throw new RuntimeException("getUtfConstant (infoParse == null) error");
            return "";
        }
        if (infoParse.getClass().equals(ConstantUtf8InfoParse.class)) { //CONSTANT_Utf8_info
            return ((ConstantUtf8InfoParse) infoParse).value;
        } else if (infoParse.getClass().equals(ConstantIntegerInfoParse.class)) { //CONSTANT_Integer_info
            return ((ConstantIntegerInfoParse) infoParse).value;
        } else if (infoParse.getClass().equals(ConstantFloatInfoParse.class)) { // CONSTANT_Float_info
            return ((ConstantFloatInfoParse) infoParse).value;
        } else if (infoParse.getClass().equals(ConstantLongInfoParse.class)) { // CONSTANT_Long_info
            return ((ConstantLongInfoParse) infoParse).value;
        } else if (infoParse.getClass().equals(ConstantDoubleInfoParse.class)) { // CONSTANT_Double_info
            return ((ConstantDoubleInfoParse) infoParse).value;
        } else if (infoParse.getClass().equals(ConstantClassInfoParse.class)) { // CONSTANT_Class_info
            return getUtfConstant(((ConstantClassInfoParse) infoParse).nameIndex);
        } else if (infoParse.getClass().equals(ConstantStringInfoParse.class)) { // CONSTANT_String_info
            return getUtfConstant(((ConstantStringInfoParse) infoParse).stringIndex);
        } else if (infoParse.getClass().equals(ConstantFieldRefInfoParse.class)) { // CONSTANT_Fieldref_info
            return getUtfConstant(((ConstantFieldRefInfoParse) infoParse).classIndex) + "  " + getUtfConstant(((ConstantFieldRefInfoParse) infoParse).nameAndTypeIndex);
        } else if (infoParse.getClass().equals(ConstantMethodRefInfoParse.class)) { // CONSTANT_Methodref_info
            return getUtfConstant(((ConstantMethodRefInfoParse) infoParse).classIndex) + "  " + getUtfConstant(((ConstantMethodRefInfoParse) infoParse).nameAndTypeIndex);
        } else if (infoParse.getClass().equals(ConstantInterfaceInfoParse.class)) { // CONSTANT_Interface-Methodref_info
            return getUtfConstant(((ConstantInterfaceInfoParse) infoParse).classIndex) + "  " + getUtfConstant(((ConstantInterfaceInfoParse) infoParse).nameAndTypeIndex);
        } else if (infoParse.getClass().equals(ConstantNameAndTypeInfoParse.class)) { // CONSTANT_NameAndType_info
            return getUtfConstant(((ConstantNameAndTypeInfoParse) infoParse).nameIndex) + "  " + getUtfConstant(((ConstantNameAndTypeInfoParse) infoParse).signatureIndex);
        } else if (infoParse.getClass().equals(ConstantMethodHandleInfoParse.class)) { // CONSTANT_Method-Handle_info
            return getUtfConstant(((ConstantMethodHandleInfoParse) infoParse).referenceIndex);
        } else if (infoParse.getClass().equals(ConstantMethodTypeInfoParse.class)) { // CONSTANT_Method-type_info
            return getUtfConstant(((ConstantMethodTypeInfoParse) infoParse).descriptorIndex);
        } else if (infoParse.getClass().equals(ConstantInvokeDynamicInfoParse.class)) { // CONSTANT_Invoke-Dynamic_info
            return getUtfConstant(((ConstantInvokeDynamicInfoParse) infoParse).bootstrapMethodAttrIndex) + "  " + getUtfConstant(((ConstantInvokeDynamicInfoParse) infoParse).nameAndTypeIndex);
        }
        throw new RuntimeException("getUtfConstant error! ");

    }
}
