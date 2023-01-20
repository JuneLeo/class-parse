# class-parse

# Example
* Person
```java
public class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void play(String name) {
        if (name.equals("basket")) {
            System.out.println("篮球");
        }

    }
}
```
* Person 解析
```
magic: CAFEBABE
minor: 0
major: 52
Constant pool: 
    #1 = [ name=CONSTANT_Methodref_info, classIndex=10, nameAndTypeIndex=27 ]
    #2 = [ name=CONSTANT_Fieldref_info, classIndex=9, nameAndTypeIndex=28 ]
    #3 = [ name=CONSTANT_Fieldref_info, classIndex=9, nameAndTypeIndex=29 ]
    #4 = [ name=CONSTANT_String_info, index=30 ]
    #5 = [ name=CONSTANT_Methodref_info, classIndex=31, nameAndTypeIndex=32 ]
    #6 = [ name=CONSTANT_Fieldref_info, classIndex=33, nameAndTypeIndex=34 ]
    #7 = [ name=CONSTANT_String_info, index=35 ]
    #8 = [ name=CONSTANT_Methodref_info, classIndex=36, nameAndTypeIndex=37 ]
    #9 = [ name=CONSTANT_Class_info, index=38 ]
    #10 = [ name=CONSTANT_Class_info, index=39 ]
    #11 = [ name=CONSTANT_Utf8_info, length=4, value='name ]
    #12 = [ name=CONSTANT_Utf8_info, length=18, value='Ljava/lang/String; ]
    #13 = [ name=CONSTANT_Utf8_info, length=3, value='age ]
    #14 = [ name=CONSTANT_Utf8_info, length=1, value='I ]
    #15 = [ name=CONSTANT_Utf8_info, length=6, value='<init> ]
    #16 = [ name=CONSTANT_Utf8_info, length=22, value='(Ljava/lang/String;I)V ]
    #17 = [ name=CONSTANT_Utf8_info, length=4, value='Code ]
    #18 = [ name=CONSTANT_Utf8_info, length=15, value='LineNumberTable ]
    #19 = [ name=CONSTANT_Utf8_info, length=18, value='LocalVariableTable ]
    #20 = [ name=CONSTANT_Utf8_info, length=4, value='this ]
    #21 = [ name=CONSTANT_Utf8_info, length=20, value='Lorg/example/Person; ]
    #22 = [ name=CONSTANT_Utf8_info, length=4, value='play ]
    #23 = [ name=CONSTANT_Utf8_info, length=21, value='(Ljava/lang/String;)V ]
    #24 = [ name=CONSTANT_Utf8_info, length=13, value='StackMapTable ]
    #25 = [ name=CONSTANT_Utf8_info, length=10, value='SourceFile ]
    #26 = [ name=CONSTANT_Utf8_info, length=11, value='Person.java ]
    #27 = [ name=CONSTANT_NameAndType_info, nameIndex=15, signatureIndex=40 ]
    #28 = [ name=CONSTANT_NameAndType_info, nameIndex=11, signatureIndex=12 ]
    #29 = [ name=CONSTANT_NameAndType_info, nameIndex=13, signatureIndex=14 ]
    #30 = [ name=CONSTANT_Utf8_info, length=6, value='basket ]
    #31 = [ name=CONSTANT_Class_info, index=41 ]
    #32 = [ name=CONSTANT_NameAndType_info, nameIndex=42, signatureIndex=43 ]
    #33 = [ name=CONSTANT_Class_info, index=44 ]
    #34 = [ name=CONSTANT_NameAndType_info, nameIndex=45, signatureIndex=46 ]
    #35 = [ name=CONSTANT_Utf8_info, length=6, value='篮球 ]
    #36 = [ name=CONSTANT_Class_info, index=47 ]
    #37 = [ name=CONSTANT_NameAndType_info, nameIndex=48, signatureIndex=23 ]
    #38 = [ name=CONSTANT_Utf8_info, length=18, value='org/example/Person ]
    #39 = [ name=CONSTANT_Utf8_info, length=16, value='java/lang/Object ]
    #40 = [ name=CONSTANT_Utf8_info, length=3, value='()V ]
    #41 = [ name=CONSTANT_Utf8_info, length=16, value='java/lang/String ]
    #42 = [ name=CONSTANT_Utf8_info, length=6, value='equals ]
    #43 = [ name=CONSTANT_Utf8_info, length=21, value='(Ljava/lang/Object;)Z ]
    #44 = [ name=CONSTANT_Utf8_info, length=16, value='java/lang/System ]
    #45 = [ name=CONSTANT_Utf8_info, length=3, value='out ]
    #46 = [ name=CONSTANT_Utf8_info, length=21, value='Ljava/io/PrintStream; ]
    #47 = [ name=CONSTANT_Utf8_info, length=19, value='java/io/PrintStream ]
    #48 = [ name=CONSTANT_Utf8_info, length=7, value='println ]
flags: ACC_PUBLIC,ACC_SUPER
---------------------------------------
flags: ACC_PUBLIC
filed: name
descriptor: Ljava/lang/String;
---------------------------------------
flags: ACC_PUBLIC
filed: age
descriptor: I
---------------------------------------
flags: ACC_PUBLIC
method: <init>
descriptor: (Ljava/lang/String;I)V
Code:
    maxStack=2  maxLocals=3
        0: aload_0
        1: invokespecial java/lang/Object  <init>  ()V
        4: aload_0
        5: aload_1
        6: putfield org/example/Person  name  Ljava/lang/String;
        9: aload_0
        10: iload_2
        11: putfield org/example/Person  age  I
        14: return
LineNumberTable:
    line 8：0
    line 9：4
    line 10：9
    line 11：14
LocalVariableTable:
    Start=0, Length=15, Slot=0, nameIndex=this, descriptorIndex=Lorg/example/Person;
    Start=0, Length=15, Slot=1, nameIndex=name, descriptorIndex=Ljava/lang/String;
    Start=0, Length=15, Slot=2, nameIndex=age, descriptorIndex=I
---------------------------------------
flags: ACC_PUBLIC
method: play
descriptor: (Ljava/lang/String;)V
Code:
    maxStack=2  maxLocals=2
        0: aload_1
        1: ldc basket
        3: invokevirtual java/lang/String  equals  (Ljava/lang/Object;)Z
        6: ifeq name
        9: getstatic java/lang/System  out  Ljava/io/PrintStream;
        12: ldc 篮球
        14: invokevirtual java/io/PrintStream  println  (Ljava/lang/String;)V
        17: return
LineNumberTable:
    line 15：0
    line 16：9
    line 18：17
LocalVariableTable:
    Start=0, Length=18, Slot=0, nameIndex=this, descriptorIndex=Lorg/example/Person;
    Start=0, Length=18, Slot=1, nameIndex=name, descriptorIndex=Ljava/lang/String;
StackMapTable:
    frame_type=17
---------------------------------------
SourceFile: 
    Person.java
```
# 解析接口
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

