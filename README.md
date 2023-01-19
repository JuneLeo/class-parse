# class-parse

```java
public interface Parse {
int parse(int start, byte[] code);
}
```
* code：为class字节码
* start：解析器开始解析位置
* return：下一个解析器开始解析的位置


# ClassFormat
```java
public class ClassFormat {
private List<Parse> parses = new ArrayList<>();

    public ClassFormat() {
        parses.add(new MagicParse()); //magic解析器
        parses.add(new VersionParse()); // 版本解析器
        parses.add(new ConstantParse()); // 常量池解析器
        parses.add(new AccessFlagParse()); // class access flag解析器
        parses.add(new ClassExtParse());  // class info解析器
        parses.add(new InterfaceParse()); // class的接口解析器
        parses.add(new FieldParse()); // field变量解析器
        parses.add(new MethodParse());   // method方法解析器
        parses.add(new AttributeParse());  // 类的其他属性解析器
    }


    public void parse(byte[] bytes) {
        int start = 0;
        for (Parse pars : parses) {
            start = pars.parse(start, bytes);
        }
    }
}
```
# MagicParse
```java
public class MagicParse implements Parse { //u4

    public String magic;

    @Override
    public int parse(int start, byte[] bytes) {
        int magicLength = 4; // magic 长度为u4
        byte[] magicCode = new byte[magicLength];
        System.arraycopy(bytes, start, magicCode, 0, magicLength);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : magicCode) {
            int value = Byte.toUnsignedInt(b);
            stringBuilder.append(Integer.toHexString(value).toUpperCase());
        }

        if (!"CAFEBABE".equals(stringBuilder.toString())) {
            throw new RuntimeException("class error");
        }

        magic = stringBuilder.toString();

        return start + magicLength;
    }

    @Override
    public String toString() {
        return "MagicParse{" +
                "magic='" + magic + '\'' +
                '}';
    }
}
```
# VersionParse
```java
public class VersionParse implements Parse {
public int minor; // u2
public int major; // u2

    @Override
    public int parse(int start, byte[] bytes) {
        minor = Utils.getU2Int(start, bytes); // u2 minor
        start += 2;
        major = Utils.getU2Int(start, bytes); // u2 major
        return start + 2;
    }

    @Override
    public String toString() {
        return "VersionParse{" +
                "minor=" + minor +
                ", major=" + major +
                '}';
    }
}
```
# getU2Int
```java
public static int getU2Int(int start, byte[] bytes) {
        byte[] versionCode = new byte[2];
        System.arraycopy(bytes, start, versionCode, 0, 2);// 2个字节
        int high = Byte.toUnsignedInt(versionCode[0]);  // 第一个字节是高位，这里将byte转位无符号的int
        int low = Byte.toUnsignedInt(versionCode[1]);  // 第二个字节是低位，这里将byte转位无符号的int
        return high << 8 | low;    // 高位左移8位 ｜ 低位就是int值
}
```

# Code解析
```java
public class CodeParse extends AttributeFormatParse {
    public int attributeNameIndex;
    public int attributeLength;
    public int maxStack;
    public int maxLocals;
    public int codeLength;

    public int exceptionTableLength;

    public int attributesCount;


    private ConstantParse constantParse;

    public CodeParse(int length, ConstantParse constantParse) {
        super(length);
        this.constantParse = constantParse;
    }

    @Override
    public int parse(int start, byte[] code) {
        int index = start;
        maxStack = Utils.getU2Int(index, code);//u2
        index += 2;
        maxLocals = Utils.getU2Int(index, code); //u2
        index += 2;
        codeLength = Utils.getU4Int(index, code); //u4
        index += 4;

        int l = index + codeLength;
        while (index < l) {
            int cmd = Utils.getU1Int(index, code);
            index += 1;
            String cmdStr = Utils.getCMD(cmd); // 这里是复制的asm中的字节码数组
            System.out.print(cmdStr);
            int paramsLength = Utils.getParamsLength(cmd); // 这里是复制的asm中的字节码后面参数的length数组
            if (paramsLength >= 0) {
                if (paramsLength == 1) {
                    int u1Int = Utils.getU1Int(index, code);
                    Object utfConstant = constantParse.getUtfConstant(u1Int);
                    System.out.print(" " + String.valueOf(utfConstant));
                } else if (paramsLength == 2) {
                    int u2Int = Utils.getU2Int(index, code);
                    Object utfConstant = constantParse.getUtfConstant(u2Int);
                    System.out.print(" " + String.valueOf(utfConstant));
                } else {
                    for (int i = 0; i < paramsLength; i++) {
                        System.out.print(" " + Utils.getU1Int(index + i, code));
                    }
                }
                index += paramsLength;
            } else {
                break;
            }
            System.out.print("\n");
        }
        index += codeLength;

    	// 这里是 exception和attribute，attribute主要解析LineNumberTable和LocalVariableTable即可，后续实现
//        exceptionTableLength = Utils.getU2Int(index, code);
//        index += 2;
//        // todo exception info
//        attributeLength = Utils.getU2Int(index, code);
//        index += 2;
//        // todo attribute


        return start + length;
    }
}

```

