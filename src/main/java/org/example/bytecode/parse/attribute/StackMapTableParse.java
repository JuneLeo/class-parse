package org.example.bytecode.parse.attribute;

import org.example.bytecode.Utils;
import org.example.bytecode.parse.ConstantParse;
import org.example.bytecode.parse.constant.Parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackMapTableParse extends AttributeFormatParse {
    private ConstantParse constantParse;
    public int numberOfEntries;

    public List<StackMapFrameParse> stackMapFrameParses = new ArrayList<>();

    public StackMapTableParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        numberOfEntries = Utils.getU2Int(start, code);
        start += 2;
        System.out.println("StackMapTable:");
        for (int i = 0; i < numberOfEntries; i++) {
            int frameType = Utils.getU1Int(start, code);
            start += 1;
            StackMapFrameParse stackMapFrameParse;
            if (frameType <= 63) {
                stackMapFrameParse = new SameFrameParse(frameType, constantParse);
            } else if (frameType <= 127) {
                stackMapFrameParse = new SameLocals1StackItemFrame(frameType, constantParse);
            } else if (frameType <= 246) {
                throw new Error("unknown frame_type " + frameType);
            } else if (frameType == 247) {
                stackMapFrameParse = new SameLocals1StackItemFrameExtended(frameType, constantParse);
            } else if (frameType <= 250) {
                stackMapFrameParse = new ChopFrame(frameType, constantParse);
            } else if (frameType == 251) {
                stackMapFrameParse = new SameFrameExtended(frameType, constantParse);
            } else if (frameType <= 254) {
                stackMapFrameParse = new AppendFrame(frameType, constantParse);
            } else {
                stackMapFrameParse = new FullFrame(frameType, constantParse);
            }

            start = stackMapFrameParse.parse(start, code);
            stackMapFrameParses.add(stackMapFrameParse);
            System.out.println(stackMapFrameParse);
        }

        return start;
    }


    private static abstract class StackMapFrameParse implements Parse {

        protected ConstantParse constantParse;
        protected int frameType;

        public StackMapFrameParse(int frameType, ConstantParse constantParse) {
            this.constantParse = constantParse;
            this.frameType = frameType;
        }


        protected String getList(List<VerificationTypeInfoParse> localsVerificationTypeInfos) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ ");
            for (int i = 0; i < localsVerificationTypeInfos.size(); i++) {
                stringBuilder.append(localsVerificationTypeInfos.get(i));
                if (i != localsVerificationTypeInfos.size() - 1) {
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append(" ]");
            return stringBuilder.toString();
        }
    }

    private static class SameFrameParse extends StackMapFrameParse {

        public SameFrameParse(int frameType, ConstantParse constantParse) {
            super(frameType, constantParse);
        }

        @Override
        public int parse(int start, byte[] code) {
            return start;
        }

        @Override
        public String toString() {
            return "    frame_type=" + frameType;
        }
    }

    private static class SameLocals1StackItemFrame extends StackMapFrameParse {
        public VerificationTypeInfoParse stackVerificationTypeInfoParse;

        public SameLocals1StackItemFrame(int frameType, ConstantParse constantParse) {
            super(frameType, constantParse);
        }

        @Override
        public int parse(int start, byte[] code) {
            stackVerificationTypeInfoParse = VerificationTypeInfoParse.get(start, code, constantParse);
            start += 1;
            start = stackVerificationTypeInfoParse.parse(start, code);
            return start;
        }

        @Override
        public String toString() {
            return "    frame_type=" + frameType + "\n        " +
                    "stack=" + getList(Arrays.asList(stackVerificationTypeInfoParse));
        }
    }

    private static class SameLocals1StackItemFrameExtended extends StackMapFrameParse {
        public int offsetDelta;

        public VerificationTypeInfoParse stackVerificationTypeInfoParse;

        public SameLocals1StackItemFrameExtended(int frameType, ConstantParse constantParse) {
            super(frameType, constantParse);
        }

        @Override
        public int parse(int start, byte[] code) {
            offsetDelta = Utils.getU2Int(start, code);
            start += 2;
            stackVerificationTypeInfoParse = VerificationTypeInfoParse.get(start, code, constantParse);
            start += 1;
            start = stackVerificationTypeInfoParse.parse(start, code);
            return start;
        }

        @Override
        public String toString() {
            return "    frame_type=" + frameType + "\n        " +
                    "offset_delta=" + offsetDelta + "\n        " +
                    "stack=" + getList(Arrays.asList(stackVerificationTypeInfoParse));
        }
    }

    private static class ChopFrame extends StackMapFrameParse {
        public int offsetDelta;

        public ChopFrame(int frameType, ConstantParse constantParse) {
            super(frameType, constantParse);
        }

        @Override
        public int parse(int start, byte[] code) {
            offsetDelta = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }

        @Override
        public String toString() {
            return "    frame_type=" + frameType + "\n        " +
                    "offset_delta=" + offsetDelta;
        }
    }

    private static class SameFrameExtended extends StackMapFrameParse {
        public int offsetDelta;

        public SameFrameExtended(int frameType, ConstantParse constantParse) {
            super(frameType, constantParse);
        }

        @Override
        public int parse(int start, byte[] code) {
            offsetDelta = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }

        @Override
        public String toString() {
            return "    frame_type=" + frameType + "\n        " +
                    "offset_delta=" + offsetDelta;
        }
    }

    private static class AppendFrame extends StackMapFrameParse {

        public int offsetDelta;
        public int numberOfLocals;
        public List<VerificationTypeInfoParse> localsVerificationTypeInfos = new ArrayList<>();

        public AppendFrame(int frameType, ConstantParse constantParse) {
            super(frameType, constantParse);
        }

        @Override
        public int parse(int start, byte[] code) {
            offsetDelta = Utils.getU2Int(start, code);
            start += 2;
            numberOfLocals = frameType - 251;
            for (int i = 0; i < numberOfLocals; i++) {
                VerificationTypeInfoParse verificationTypeInfoParse = VerificationTypeInfoParse.get(start, code, constantParse);
                start += 1;
                start = verificationTypeInfoParse.parse(start, code);
                localsVerificationTypeInfos.add(verificationTypeInfoParse);
            }
            return start;
        }


        @Override
        public String toString() {
            return "    frame_type=" + frameType + "\n        " +
                    "offset_delta=" + offsetDelta + "\n        " +
                    "locals=" + getList(localsVerificationTypeInfos);
        }
    }

    private static class FullFrame extends StackMapFrameParse {
        public int offsetDelta;
        public int numberOfLocals;
        public List<VerificationTypeInfoParse> localsVerificationTypeInfos = new ArrayList<>();
        public int numberOfStackItems;

        public List<VerificationTypeInfoParse> stackItemsVerificationTypeInfos = new ArrayList<>();

        public FullFrame(int frameType, ConstantParse constantParse) {
            super(frameType, constantParse);
        }

        @Override
        public int parse(int start, byte[] code) {
            offsetDelta = Utils.getU2Int(start, code);
            start += 2;
            numberOfLocals = Utils.getU2Int(start, code);
            start += 2;
            for (int i = 0; i < numberOfLocals; i++) {
                VerificationTypeInfoParse verificationTypeInfoParse = VerificationTypeInfoParse.get(start, code, constantParse);
                start += 1;
                start = verificationTypeInfoParse.parse(start, code);
                localsVerificationTypeInfos.add(verificationTypeInfoParse);
            }

            numberOfStackItems = Utils.getU2Int(start, code);
            start += 2;
            for (int i = 0; i < numberOfStackItems; i++) {
                VerificationTypeInfoParse verificationTypeInfoParse = VerificationTypeInfoParse.get(start, code, constantParse);
                start += 1;
                start = verificationTypeInfoParse.parse(start, code);
                stackItemsVerificationTypeInfos.add(verificationTypeInfoParse);
            }

            return start;
        }

        @Override
        public String toString() {
            return "    frame_type=" + frameType + "\n        " +
                    "offset_delta=" + offsetDelta + "\n        " +
                    "locals=" + getList(localsVerificationTypeInfos) + "\n        " +
                    "stack=" + getList(stackItemsVerificationTypeInfos);
        }


    }


    private static class VerificationTypeInfoParse implements Parse {
        public int tag;
        protected ConstantParse constantParse;


        private static final int ITEM_Top = 0;
        private static final int ITEM_Integer = 1;
        private static final int ITEM_Float = 2;
        private static final int ITEM_Long = 4;
        private static final int ITEM_Double = 3;
        private static final int ITEM_Null = 5;
        private static final int ITEM_UninitializedThis = 6;
        private static final int ITEM_Object = 7;
        private static final int ITEM_Uninitialized = 8;

        public VerificationTypeInfoParse(int tag, ConstantParse constantParse) {
            this.tag = tag;
            this.constantParse = constantParse;
        }

        public static VerificationTypeInfoParse get(int start, byte[] code, ConstantParse constantParse) {
            int tag = Utils.getU1Int(start, code);
            switch (tag) {
                case ITEM_Top:
                case ITEM_Integer:
                case ITEM_Float:
                case ITEM_Long:
                case ITEM_Double:
                case ITEM_Null:
                case ITEM_UninitializedThis:
                    return new VerificationTypeInfoParse(tag, constantParse);
                case ITEM_Object:
                    return new VerificationTypeObjectInfoParse(tag, constantParse);
                case ITEM_Uninitialized:
                    return new VerificationTypeUninitializedInfoParse(tag, constantParse);
                default:
                    throw new RuntimeException("VerificationTypeInfoParse parse error!");
            }

        }

        @Override
        public int parse(int start, byte[] code) {
            return start;
        }

        @Override
        public String toString() {
            return tag + "";
        }
    }


    private static class VerificationTypeObjectInfoParse extends VerificationTypeInfoParse {
        public int cpoolIndex;

        public VerificationTypeObjectInfoParse(int tag, ConstantParse constantParse) {
            super(tag, constantParse);
        }


        @Override
        public int parse(int start, byte[] code) {
            cpoolIndex = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }

        @Override
        public String toString() {
            return Utils.getUtf(cpoolIndex, constantParse);
        }
    }


    private static class VerificationTypeUninitializedInfoParse extends VerificationTypeInfoParse {

        public int offset;

        public VerificationTypeUninitializedInfoParse(int tag, ConstantParse constantParse) {
            super(tag, constantParse);
        }

        @Override
        public int parse(int start, byte[] code) {
            offset = Utils.getU2Int(start, code);
            start += 2;
            return start;
        }

        @Override
        public String toString() {
            return "offset=" + offset;
        }
    }


}
