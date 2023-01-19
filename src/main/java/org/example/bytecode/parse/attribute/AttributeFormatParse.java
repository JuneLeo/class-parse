package org.example.bytecode.parse.attribute;

import org.example.bytecode.parse.constant.Parse;

public abstract class AttributeFormatParse implements Parse {
    int length;

    public AttributeFormatParse(int length) {
        this.length = length;
    }
}
