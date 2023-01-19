package org.example.bytecode;


import org.example.bytecode.parse.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import org.example.bytecode.parse.constant.Parse;

public class ClassFormat {
    private List<Parse> parses = new ArrayList<>();

    public ClassFormat() {
        parses.add(new MagicParse());
        parses.add(new VersionParse());
        ConstantParse constantParse = new ConstantParse();
        parses.add(constantParse);
        parses.add(new AccessFlagParse());
        parses.add(new ClassExtParse());
        parses.add(new InterfaceParse());
        parses.add(new FieldParse(constantParse));
        parses.add(new MethodParse(constantParse));
        parses.add(new AttributeParse(constantParse));
    }





    public void parse(byte[] bytes) {
        int start = 0;
        for (Parse pars : parses) {
            start = pars.parse(start, bytes);
        }
    }
}
