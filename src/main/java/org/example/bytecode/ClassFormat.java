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
        parses.add(new ConstantParse());
        parses.add(new AccessFlagParse());
        parses.add(new ClassExtParse());
        parses.add(new InterfaceParse());
        parses.add(new FieldParse());
        parses.add(new MethodParse());
        parses.add(new AttributeParse());
    }


    public void parse(byte[] bytes) {
        int start = 0;
        for (Parse pars : parses) {
            start = pars.parse(start, bytes);
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(parses));
    }
}
