package ast;

import ui.Main;

public class PROCCALL extends STATEMENT {
    private String name;

    @Override
    public void parse() {
       tokenizer.getAndCheckNext("call");
       name = tokenizer.getNext();

    }

    @Override
    public Integer evaluate() {
        PROCBODY proBODY = (PROCBODY) Main.symbolTable.get(name);
        proBODY.evaluate();
        return null;
    }
    
}