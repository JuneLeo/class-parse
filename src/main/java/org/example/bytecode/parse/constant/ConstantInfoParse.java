package org.example.bytecode.parse.constant;

public abstract class ConstantInfoParse implements Index,Parse {
    public int tag;
    public String name;
    public int index;
    public int realIndex;


    public ConstantInfoParse(int tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
    public void setRealIndex(int index) {
        this.realIndex = index;
    }
}
