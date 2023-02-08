package org.example.bytecode;

import org.example.bytecode.parse.ConstantParse;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Objects;

public class Utils {


    public static int getU1Int(int start, byte[] bytes) {
        byte[] versionCode = new byte[1];
        System.arraycopy(bytes, start, versionCode, 0, 1);
        return Byte.toUnsignedInt(versionCode[0]);
    }

    public static int getU2Int(int start, byte[] bytes) {
        byte[] versionCode = new byte[2];
        System.arraycopy(bytes, start, versionCode, 0, 2);
        int high = Byte.toUnsignedInt(versionCode[0]);
        int low = Byte.toUnsignedInt(versionCode[1]);
        return high << 8 | low;
    }


    public static int getU4Int(int start, byte[] bytes) {
        int length = 4;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put(bytes, start, length);
        buffer.flip();
        return buffer.getInt();
    }


    public static long getU8Long(int start, byte[] bytes) {
        int length = 8;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put(bytes, start, length);
        buffer.flip();
        return buffer.getLong();
    }


    public static float getU4Float(int start, byte[] bytes) {
        int length = 4;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put(bytes, start, length);
        buffer.flip();
        return buffer.getFloat();
    }


    public static double getU8Double(int start, byte[] bytes) {
        int length = 8;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put(bytes, start, length);
        buffer.flip();
        return buffer.getDouble();
    }


    public static <T> String getListToString(Collection<T> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        for (T objects : collection) {
            stringBuilder.append(objects.toString());
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    public static final String ILLEGAL_OPCODE = "<illegal opcode>";
    public static final short UNDEFINED = -1;
    public static final short UNPREDICTABLE = -2;
    public static final short RESERVED = -3;
    /**
     * from asm
     */
    public static final String[] OPCODE_NAMES = {
            "nop", "aconst_null", "iconst_m1", "iconst_0", "iconst_1",
            "iconst_2", "iconst_3", "iconst_4", "iconst_5", "lconst_0",
            "lconst_1", "fconst_0", "fconst_1", "fconst_2", "dconst_0",
            "dconst_1", "bipush", "sipush", "ldc", "ldc_w", "ldc2_w", "iload",
            "lload", "fload", "dload", "aload", "iload_0", "iload_1", "iload_2",
            "iload_3", "lload_0", "lload_1", "lload_2", "lload_3", "fload_0",
            "fload_1", "fload_2", "fload_3", "dload_0", "dload_1", "dload_2",
            "dload_3", "aload_0", "aload_1", "aload_2", "aload_3", "iaload",
            "laload", "faload", "daload", "aaload", "baload", "caload", "saload",
            "istore", "lstore", "fstore", "dstore", "astore", "istore_0",
            "istore_1", "istore_2", "istore_3", "lstore_0", "lstore_1",
            "lstore_2", "lstore_3", "fstore_0", "fstore_1", "fstore_2",
            "fstore_3", "dstore_0", "dstore_1", "dstore_2", "dstore_3",
            "astore_0", "astore_1", "astore_2", "astore_3", "iastore", "lastore",
            "fastore", "dastore", "aastore", "bastore", "castore", "sastore",
            "pop", "pop2", "dup", "dup_x1", "dup_x2", "dup2", "dup2_x1",
            "dup2_x2", "swap", "iadd", "ladd", "fadd", "dadd", "isub", "lsub",
            "fsub", "dsub", "imul", "lmul", "fmul", "dmul", "idiv", "ldiv",
            "fdiv", "ddiv", "irem", "lrem", "frem", "drem", "ineg", "lneg",
            "fneg", "dneg", "ishl", "lshl", "ishr", "lshr", "iushr", "lushr",
            "iand", "land", "ior", "lor", "ixor", "lxor", "iinc", "i2l", "i2f",
            "i2d", "l2i", "l2f", "l2d", "f2i", "f2l", "f2d", "d2i", "d2l", "d2f",
            "i2b", "i2c", "i2s", "lcmp", "fcmpl", "fcmpg",
            "dcmpl", "dcmpg", "ifeq", "ifne", "iflt", "ifge", "ifgt", "ifle",
            "if_icmpeq", "if_icmpne", "if_icmplt", "if_icmpge", "if_icmpgt",
            "if_icmple", "if_acmpeq", "if_acmpne", "goto", "jsr", "ret",
            "tableswitch", "lookupswitch", "ireturn", "lreturn", "freturn",
            "dreturn", "areturn", "return", "getstatic", "putstatic", "getfield",
            "putfield", "invokevirtual", "invokespecial", "invokestatic",
            "invokeinterface", "invokedynamic", "new", "newarray", "anewarray",
            "arraylength", "athrow", "checkcast", "instanceof", "monitorenter",
            "monitorexit", "wide", "multianewarray", "ifnull", "ifnonnull",
            "goto_w", "jsr_w", "breakpoint", ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE, ILLEGAL_OPCODE,
            ILLEGAL_OPCODE, "impdep1", "impdep2"
    };


    public static String getCMD(int cmd) {
        return OPCODE_NAMES[cmd];
    }

    /**
     * from asm
     */
    public static final short[] NO_OF_OPERANDS = {
            0/*nop*/, 0/*aconst_null*/, 0/*iconst_m1*/, 0/*iconst_0*/,
            0/*iconst_1*/, 0/*iconst_2*/, 0/*iconst_3*/, 0/*iconst_4*/,
            0/*iconst_5*/, 0/*lconst_0*/, 0/*lconst_1*/, 0/*fconst_0*/,
            0/*fconst_1*/, 0/*fconst_2*/, 0/*dconst_0*/, 0/*dconst_1*/,
            1/*bipush*/, 2/*sipush*/, 1/*ldc*/, 2/*ldc_w*/, 2/*ldc2_w*/,
            1/*iload*/, 1/*lload*/, 1/*fload*/, 1/*dload*/, 1/*aload*/,
            0/*iload_0*/, 0/*iload_1*/, 0/*iload_2*/, 0/*iload_3*/,
            0/*lload_0*/, 0/*lload_1*/, 0/*lload_2*/, 0/*lload_3*/,
            0/*fload_0*/, 0/*fload_1*/, 0/*fload_2*/, 0/*fload_3*/,
            0/*dload_0*/, 0/*dload_1*/, 0/*dload_2*/, 0/*dload_3*/,
            0/*aload_0*/, 0/*aload_1*/, 0/*aload_2*/, 0/*aload_3*/,
            0/*iaload*/, 0/*laload*/, 0/*faload*/, 0/*daload*/,
            0/*aaload*/, 0/*baload*/, 0/*caload*/, 0/*saload*/,
            1/*istore*/, 1/*lstore*/, 1/*fstore*/, 1/*dstore*/,
            1/*astore*/, 0/*istore_0*/, 0/*istore_1*/, 0/*istore_2*/,
            0/*istore_3*/, 0/*lstore_0*/, 0/*lstore_1*/, 0/*lstore_2*/,
            0/*lstore_3*/, 0/*fstore_0*/, 0/*fstore_1*/, 0/*fstore_2*/,
            0/*fstore_3*/, 0/*dstore_0*/, 0/*dstore_1*/, 0/*dstore_2*/,
            0/*dstore_3*/, 0/*astore_0*/, 0/*astore_1*/, 0/*astore_2*/,
            0/*astore_3*/, 0/*iastore*/, 0/*lastore*/, 0/*fastore*/,
            0/*dastore*/, 0/*aastore*/, 0/*bastore*/, 0/*castore*/,
            0/*sastore*/, 0/*pop*/, 0/*pop2*/, 0/*dup*/, 0/*dup_x1*/,
            0/*dup_x2*/, 0/*dup2*/, 0/*dup2_x1*/, 0/*dup2_x2*/, 0/*swap*/,
            0/*iadd*/, 0/*ladd*/, 0/*fadd*/, 0/*dadd*/, 0/*isub*/,
            0/*lsub*/, 0/*fsub*/, 0/*dsub*/, 0/*imul*/, 0/*lmul*/,
            0/*fmul*/, 0/*dmul*/, 0/*idiv*/, 0/*ldiv*/, 0/*fdiv*/,
            0/*ddiv*/, 0/*irem*/, 0/*lrem*/, 0/*frem*/, 0/*drem*/,
            0/*ineg*/, 0/*lneg*/, 0/*fneg*/, 0/*dneg*/, 0/*ishl*/,
            0/*lshl*/, 0/*ishr*/, 0/*lshr*/, 0/*iushr*/, 0/*lushr*/,
            0/*iand*/, 0/*land*/, 0/*ior*/, 0/*lor*/, 0/*ixor*/, 0/*lxor*/,
            2/*iinc*/, 0/*i2l*/, 0/*i2f*/, 0/*i2d*/, 0/*l2i*/, 0/*l2f*/,
            0/*l2d*/, 0/*f2i*/, 0/*f2l*/, 0/*f2d*/, 0/*d2i*/, 0/*d2l*/,
            0/*d2f*/, 0/*i2b*/, 0/*i2c*/, 0/*i2s*/, 0/*lcmp*/, 0/*fcmpl*/,
            0/*fcmpg*/, 0/*dcmpl*/, 0/*dcmpg*/, 2/*ifeq*/, 2/*ifne*/,
            2/*iflt*/, 2/*ifge*/, 2/*ifgt*/, 2/*ifle*/, 2/*if_icmpeq*/,
            2/*if_icmpne*/, 2/*if_icmplt*/, 2/*if_icmpge*/, 2/*if_icmpgt*/,
            2/*if_icmple*/, 2/*if_acmpeq*/, 2/*if_acmpne*/, 2/*goto*/,
            2/*jsr*/, 1/*ret*/, UNPREDICTABLE/*tableswitch*/, UNPREDICTABLE/*lookupswitch*/,
            0/*ireturn*/, 0/*lreturn*/, 0/*freturn*/,
            0/*dreturn*/, 0/*areturn*/, 0/*return*/,
            2/*getstatic*/, 2/*putstatic*/, 2/*getfield*/,
            2/*putfield*/, 2/*invokevirtual*/, 2/*invokespecial*/, 2/*invokestatic*/,
            4/*invokeinterface*/, 4/*invokedynamic*/, 2/*new*/,
            1/*newarray*/, 2/*anewarray*/,
            0/*arraylength*/, 0/*athrow*/, 2/*checkcast*/,
            2/*instanceof*/, 0/*monitorenter*/,
            0/*monitorexit*/, UNPREDICTABLE/*wide*/, 3/*multianewarray*/,
            2/*ifnull*/, 2/*ifnonnull*/, 4/*goto_w*/,
            4/*jsr_w*/, 0/*breakpoint*/, UNDEFINED,
            UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
            UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
            UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
            UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
            UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
            UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
            UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
            UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED,
            UNDEFINED, UNDEFINED, RESERVED/*impdep1*/, RESERVED/*impdep2*/
    };

    public static int getParamsLength(int cmd) {
        return NO_OF_OPERANDS[cmd];
    }

    public static final int T_BYTE = 1;
    public static final int T_SHORT = 2;
    public static final int T_INT = 4;


    public static final short[][] TYPE_OF_OPERANDS = {
            {}/*nop*/, {}/*aconst_null*/, {}/*iconst_m1*/, {}/*iconst_0*/,
            {}/*iconst_1*/, {}/*iconst_2*/, {}/*iconst_3*/, {}/*iconst_4*/,
            {}/*iconst_5*/, {}/*lconst_0*/, {}/*lconst_1*/, {}/*fconst_0*/,
            {}/*fconst_1*/, {}/*fconst_2*/, {}/*dconst_0*/, {}/*dconst_1*/,
            {T_BYTE}/*bipush*/, {T_SHORT}/*sipush*/, {T_BYTE}/*ldc*/,
            {T_SHORT}/*ldc_w*/, {T_SHORT}/*ldc2_w*/,
            {T_BYTE}/*iload*/, {T_BYTE}/*lload*/, {T_BYTE}/*fload*/,
            {T_BYTE}/*dload*/, {T_BYTE}/*aload*/, {}/*iload_0*/,
            {}/*iload_1*/, {}/*iload_2*/, {}/*iload_3*/, {}/*lload_0*/,
            {}/*lload_1*/, {}/*lload_2*/, {}/*lload_3*/, {}/*fload_0*/,
            {}/*fload_1*/, {}/*fload_2*/, {}/*fload_3*/, {}/*dload_0*/,
            {}/*dload_1*/, {}/*dload_2*/, {}/*dload_3*/, {}/*aload_0*/,
            {}/*aload_1*/, {}/*aload_2*/, {}/*aload_3*/, {}/*iaload*/,
            {}/*laload*/, {}/*faload*/, {}/*daload*/, {}/*aaload*/,
            {}/*baload*/, {}/*caload*/, {}/*saload*/, {T_BYTE}/*istore*/,
            {T_BYTE}/*lstore*/, {T_BYTE}/*fstore*/, {T_BYTE}/*dstore*/,
            {T_BYTE}/*astore*/, {}/*istore_0*/, {}/*istore_1*/,
            {}/*istore_2*/, {}/*istore_3*/, {}/*lstore_0*/, {}/*lstore_1*/,
            {}/*lstore_2*/, {}/*lstore_3*/, {}/*fstore_0*/, {}/*fstore_1*/,
            {}/*fstore_2*/, {}/*fstore_3*/, {}/*dstore_0*/, {}/*dstore_1*/,
            {}/*dstore_2*/, {}/*dstore_3*/, {}/*astore_0*/, {}/*astore_1*/,
            {}/*astore_2*/, {}/*astore_3*/, {}/*iastore*/, {}/*lastore*/,
            {}/*fastore*/, {}/*dastore*/, {}/*aastore*/, {}/*bastore*/,
            {}/*castore*/, {}/*sastore*/, {}/*pop*/, {}/*pop2*/, {}/*dup*/,
            {}/*dup_x1*/, {}/*dup_x2*/, {}/*dup2*/, {}/*dup2_x1*/,
            {}/*dup2_x2*/, {}/*swap*/, {}/*iadd*/, {}/*ladd*/, {}/*fadd*/,
            {}/*dadd*/, {}/*isub*/, {}/*lsub*/, {}/*fsub*/, {}/*dsub*/,
            {}/*imul*/, {}/*lmul*/, {}/*fmul*/, {}/*dmul*/, {}/*idiv*/,
            {}/*ldiv*/, {}/*fdiv*/, {}/*ddiv*/, {}/*irem*/, {}/*lrem*/,
            {}/*frem*/, {}/*drem*/, {}/*ineg*/, {}/*lneg*/, {}/*fneg*/,
            {}/*dneg*/, {}/*ishl*/, {}/*lshl*/, {}/*ishr*/, {}/*lshr*/,
            {}/*iushr*/, {}/*lushr*/, {}/*iand*/, {}/*land*/, {}/*ior*/,
            {}/*lor*/, {}/*ixor*/, {}/*lxor*/, {T_BYTE, T_BYTE}/*iinc*/,
            {}/*i2l*/, {}/*i2f*/, {}/*i2d*/, {}/*l2i*/, {}/*l2f*/, {}/*l2d*/,
            {}/*f2i*/, {}/*f2l*/, {}/*f2d*/, {}/*d2i*/, {}/*d2l*/, {}/*d2f*/,
            {}/*i2b*/, {}/*i2c*/, {}/*i2s*/, {}/*lcmp*/, {}/*fcmpl*/,
            {}/*fcmpg*/, {}/*dcmpl*/, {}/*dcmpg*/, {T_SHORT}/*ifeq*/,
            {T_SHORT}/*ifne*/, {T_SHORT}/*iflt*/, {T_SHORT}/*ifge*/,
            {T_SHORT}/*ifgt*/, {T_SHORT}/*ifle*/, {T_SHORT}/*if_icmpeq*/,
            {T_SHORT}/*if_icmpne*/, {T_SHORT}/*if_icmplt*/,
            {T_SHORT}/*if_icmpge*/, {T_SHORT}/*if_icmpgt*/,
            {T_SHORT}/*if_icmple*/, {T_SHORT}/*if_acmpeq*/,
            {T_SHORT}/*if_acmpne*/, {T_SHORT}/*goto*/, {T_SHORT}/*jsr*/,
            {T_BYTE}/*ret*/, {}/*tableswitch*/, {}/*lookupswitch*/,
            {}/*ireturn*/, {}/*lreturn*/, {}/*freturn*/, {}/*dreturn*/,
            {}/*areturn*/, {}/*return*/, {T_SHORT}/*getstatic*/,
            {T_SHORT}/*putstatic*/, {T_SHORT}/*getfield*/,
            {T_SHORT}/*putfield*/, {T_SHORT}/*invokevirtual*/,
            {T_SHORT}/*invokespecial*/, {T_SHORT}/*invokestatic*/,
            {T_SHORT, T_BYTE, T_BYTE}/*invokeinterface*/, {},
            {T_SHORT}/*new*/, {T_BYTE}/*newarray*/,
            {T_SHORT}/*anewarray*/, {}/*arraylength*/, {}/*athrow*/,
            {T_SHORT}/*checkcast*/, {T_SHORT}/*instanceof*/,
            {}/*monitorenter*/, {}/*monitorexit*/, {T_BYTE}/*wide*/,
            {T_SHORT, T_BYTE}/*multianewarray*/, {T_SHORT}/*ifnull*/,
            {T_SHORT}/*ifnonnull*/, {T_INT}/*goto_w*/, {T_INT}/*jsr_w*/,
            {}/*breakpoint*/, {}, {}, {}, {}, {}, {}, {},
            {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
            {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
            {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
            {}/*impdep1*/, {}/*impdep2*/
    };


    public static short[] getParamType(int cmd) {
        return TYPE_OF_OPERANDS[cmd];
    }


    public static String getUtf(int index, ConstantParse constantParse) {
        return String.valueOf(constantParse.getUtfConstant(index));
    }

    public static final short NOP = 0;
    public static final short ACONST_NULL = 1;
    public static final short ICONST_M1 = 2;
    public static final short ICONST_0 = 3;
    public static final short ICONST_1 = 4;
    public static final short ICONST_2 = 5;
    public static final short ICONST_3 = 6;
    public static final short ICONST_4 = 7;
    public static final short ICONST_5 = 8;
    public static final short LCONST_0 = 9;
    public static final short LCONST_1 = 10;
    public static final short FCONST_0 = 11;
    public static final short FCONST_1 = 12;
    public static final short FCONST_2 = 13;
    public static final short DCONST_0 = 14;
    public static final short DCONST_1 = 15;
    public static final short BIPUSH = 16;
    public static final short SIPUSH = 17;
    public static final short LDC = 18;
    public static final short LDC_W = 19;
    public static final short LDC2_W = 20;
    public static final short ILOAD = 21;
    public static final short LLOAD = 22;
    public static final short FLOAD = 23;
    public static final short DLOAD = 24;
    public static final short ALOAD = 25;
    public static final short ILOAD_0 = 26;
    public static final short ILOAD_1 = 27;
    public static final short ILOAD_2 = 28;
    public static final short ILOAD_3 = 29;
    public static final short LLOAD_0 = 30;
    public static final short LLOAD_1 = 31;
    public static final short LLOAD_2 = 32;
    public static final short LLOAD_3 = 33;
    public static final short FLOAD_0 = 34;
    public static final short FLOAD_1 = 35;
    public static final short FLOAD_2 = 36;
    public static final short FLOAD_3 = 37;
    public static final short DLOAD_0 = 38;
    public static final short DLOAD_1 = 39;
    public static final short DLOAD_2 = 40;
    public static final short DLOAD_3 = 41;
    public static final short ALOAD_0 = 42;
    public static final short ALOAD_1 = 43;
    public static final short ALOAD_2 = 44;
    public static final short ALOAD_3 = 45;
    public static final short IALOAD = 46;
    public static final short LALOAD = 47;
    public static final short FALOAD = 48;
    public static final short DALOAD = 49;
    public static final short AALOAD = 50;
    public static final short BALOAD = 51;
    public static final short CALOAD = 52;
    public static final short SALOAD = 53;
    public static final short ISTORE = 54;
    public static final short LSTORE = 55;
    public static final short FSTORE = 56;
    public static final short DSTORE = 57;
    public static final short ASTORE = 58;
    public static final short ISTORE_0 = 59;
    public static final short ISTORE_1 = 60;
    public static final short ISTORE_2 = 61;
    public static final short ISTORE_3 = 62;
    public static final short LSTORE_0 = 63;
    public static final short LSTORE_1 = 64;
    public static final short LSTORE_2 = 65;
    public static final short LSTORE_3 = 66;
    public static final short FSTORE_0 = 67;
    public static final short FSTORE_1 = 68;
    public static final short FSTORE_2 = 69;
    public static final short FSTORE_3 = 70;
    public static final short DSTORE_0 = 71;
    public static final short DSTORE_1 = 72;
    public static final short DSTORE_2 = 73;
    public static final short DSTORE_3 = 74;
    public static final short ASTORE_0 = 75;
    public static final short ASTORE_1 = 76;
    public static final short ASTORE_2 = 77;
    public static final short ASTORE_3 = 78;
    public static final short IASTORE = 79;
    public static final short LASTORE = 80;
    public static final short FASTORE = 81;
    public static final short DASTORE = 82;
    public static final short AASTORE = 83;
    public static final short BASTORE = 84;
    public static final short CASTORE = 85;
    public static final short SASTORE = 86;
    public static final short POP = 87;
    public static final short POP2 = 88;
    public static final short DUP = 89;
    public static final short DUP_X1 = 90;
    public static final short DUP_X2 = 91;
    public static final short DUP2 = 92;
    public static final short DUP2_X1 = 93;
    public static final short DUP2_X2 = 94;
    public static final short SWAP = 95;
    public static final short IADD = 96;
    public static final short LADD = 97;
    public static final short FADD = 98;
    public static final short DADD = 99;
    public static final short ISUB = 100;
    public static final short LSUB = 101;
    public static final short FSUB = 102;
    public static final short DSUB = 103;
    public static final short IMUL = 104;
    public static final short LMUL = 105;
    public static final short FMUL = 106;
    public static final short DMUL = 107;
    public static final short IDIV = 108;
    public static final short LDIV = 109;
    public static final short FDIV = 110;
    public static final short DDIV = 111;
    public static final short IREM = 112;
    public static final short LREM = 113;
    public static final short FREM = 114;
    public static final short DREM = 115;
    public static final short INEG = 116;
    public static final short LNEG = 117;
    public static final short FNEG = 118;
    public static final short DNEG = 119;
    public static final short ISHL = 120;
    public static final short LSHL = 121;
    public static final short ISHR = 122;
    public static final short LSHR = 123;
    public static final short IUSHR = 124;
    public static final short LUSHR = 125;
    public static final short IAND = 126;
    public static final short LAND = 127;
    public static final short IOR = 128;
    public static final short LOR = 129;
    public static final short IXOR = 130;
    public static final short LXOR = 131;
    public static final short IINC = 132;
    public static final short I2L = 133;
    public static final short I2F = 134;
    public static final short I2D = 135;
    public static final short L2I = 136;
    public static final short L2F = 137;
    public static final short L2D = 138;
    public static final short F2I = 139;
    public static final short F2L = 140;
    public static final short F2D = 141;
    public static final short D2I = 142;
    public static final short D2L = 143;
    public static final short D2F = 144;
    public static final short I2B = 145;
    public static final short INT2BYTE = 145; // Old notion
    public static final short I2C = 146;
    public static final short INT2CHAR = 146; // Old notion
    public static final short I2S = 147;
    public static final short INT2SHORT = 147; // Old notion
    public static final short LCMP = 148;
    public static final short FCMPL = 149;
    public static final short FCMPG = 150;
    public static final short DCMPL = 151;
    public static final short DCMPG = 152;
    public static final short IFEQ = 153;
    public static final short IFNE = 154;
    public static final short IFLT = 155;
    public static final short IFGE = 156;
    public static final short IFGT = 157;
    public static final short IFLE = 158;
    public static final short IF_ICMPEQ = 159;
    public static final short IF_ICMPNE = 160;
    public static final short IF_ICMPLT = 161;
    public static final short IF_ICMPGE = 162;
    public static final short IF_ICMPGT = 163;
    public static final short IF_ICMPLE = 164;
    public static final short IF_ACMPEQ = 165;
    public static final short IF_ACMPNE = 166;
    public static final short GOTO = 167;
    public static final short JSR = 168;
    public static final short RET = 169;
    public static final short TABLESWITCH = 170;
    public static final short LOOKUPSWITCH = 171;
    public static final short IRETURN = 172;
    public static final short LRETURN = 173;
    public static final short FRETURN = 174;
    public static final short DRETURN = 175;
    public static final short ARETURN = 176;
    public static final short RETURN = 177;
    public static final short GETSTATIC = 178;
    public static final short PUTSTATIC = 179;
    public static final short GETFIELD = 180;
    public static final short PUTFIELD = 181;
    public static final short INVOKEVIRTUAL = 182;
    public static final short INVOKESPECIAL = 183;
    public static final short INVOKENONVIRTUAL = 183; // Old name in JDK 1.0
    public static final short INVOKESTATIC = 184;
    public static final short INVOKEINTERFACE = 185;
    public static final short NEW = 187;
    public static final short NEWARRAY = 188;
    public static final short ANEWARRAY = 189;
    public static final short ARRAYLENGTH = 190;
    public static final short ATHROW = 191;
    public static final short CHECKCAST = 192;
    public static final short INSTANCEOF = 193;
    public static final short MONITORENTER = 194;
    public static final short MONITOREXIT = 195;
    public static final short WIDE = 196;
    public static final short MULTIANEWARRAY = 197;
    public static final short IFNULL = 198;
    public static final short IFNONNULL = 199;
    public static final short GOTO_W = 200;
    public static final short JSR_W = 201;



    public static final String ILLEGAL_TYPE   = "<illegal type>";
    public static final String[] TYPE_NAMES = {
            ILLEGAL_TYPE, ILLEGAL_TYPE,  ILLEGAL_TYPE, ILLEGAL_TYPE,
            "boolean", "char", "float", "double", "byte", "short", "int", "long",
            "void", "array", "object", "unknown" // Non-standard
    };


}
