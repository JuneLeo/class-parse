package org.example.bytecode;

import java.nio.ByteBuffer;

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
}
