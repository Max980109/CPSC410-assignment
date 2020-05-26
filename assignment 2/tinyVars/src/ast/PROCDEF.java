package ast;

import java.util.Map;

import libs.Node;

public class PROCDEF extends Node {
    private String name;
    private EXP procbody;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("def");
        name = tokenizer.getNext();
        procbody = (PROCBODY) EXP.makeExp(tokenizer);
        if (!(procbody instanceof PROCBODY)) {
            throw new RuntimeException("not a procbody");
        }
        procbody.parse();
    }

    @Override
    public Integer evaluate(Map<String, Object> symbolTable) {
        System.out.println("Define procedure " + name);
        symbolTable.put(name, procbody);
        return null;
    }
}