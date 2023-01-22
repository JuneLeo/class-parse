package org.example.bytecode.parse.attribute;

import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Parse;
import org.example.bytecode.Utils;

public class AttributeInfoParse implements Parse {
    public int nameIndex;
    public int attributeLength;

    public AttributeFormatParse attributeFormatParse;

    private ConstantParse constantParse;

    public AttributeInfoParse(ConstantParse constantParse) {
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] bytes) {
        nameIndex = Utils.getU2Int(start, bytes);
        start += 2;
        attributeLength = Utils.getU4Int(start, bytes);
        start += 4;

        String utfConstant = String.valueOf(constantParse.getUtfConstant(nameIndex));

        switch (utfConstant) {
            case "Code":
                attributeFormatParse = new CodeParse(attributeLength, constantParse);
                break;
            case "LineNumberTable":
                attributeFormatParse = new LineNumberTableParse(attributeLength, constantParse);
                break;
            case "LocalVariableTable":
                attributeFormatParse = new LocalVariableTableParse(attributeLength, constantParse);
                break;
            case "SourceFile":
                attributeFormatParse = new SourceFileParse(attributeLength, constantParse);
                break;
            case "Exceptions":
                attributeFormatParse = new ExceptionsParse(attributeLength, constantParse);
                break;
            case "ConstantValue":
                attributeFormatParse = new ConstantValueParse(attributeLength, constantParse);
                break;
            case "InnerClasses":
                attributeFormatParse = new InnerClassParse(attributeLength, constantParse);
                break;
            case "Deprecated":
                attributeFormatParse = new DeprecatedParse(attributeLength, constantParse);
                break;
            case "Synthetic":
                attributeFormatParse = new SyntheticParse(attributeLength, constantParse);
                break;
            case "StackMapTable":
                attributeFormatParse = new StackMapTableParse(attributeLength, constantParse);
                break;
            case "Signature":
                attributeFormatParse = new SignatureParse(attributeLength, constantParse);
                break;
            case "BootstrapMethods":
                attributeFormatParse = new BootstrapMethodsParse(attributeLength, constantParse);
                break;
            case "LocalVariableTypeTable":
                attributeFormatParse = new LocalVariableTypeTableParse(attributeLength,constantParse);
                break;
            default:
                attributeFormatParse = new Info(attributeLength);
                break;
        }

        start = attributeFormatParse.parse(start, bytes);

        return start;
    }

    @Override
    public String toString() {
        return "AttributeInfoParse{" +
                "nameIndex=" + nameIndex +
                ", attributeLength=" + attributeLength +
                ", attributeFormatParse=" + attributeFormatParse +
                '}';
    }

    private static class Info extends AttributeFormatParse implements Parse {

        public byte[] bytes;

        public Info(int length) {
            super(length);
            bytes = new byte[length];
        }

        @Override
        public int parse(int start, byte[] bytes) {
            for (int i = 0; i < length; i++) {
                this.bytes[i] = bytes[start + i];
            }
            return start + length;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "value=" + bytes +
                    '}';
        }
    }

}
