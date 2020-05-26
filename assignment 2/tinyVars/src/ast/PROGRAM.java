package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.List;

public class PROGRAM extends Node{
    private List<STATEMENT> statements = new ArrayList<>();
    private List<PROCDEF> definitions = new ArrayList<>();

    @Override
    public void parse() {
        while (tokenizer.moreTokens()) {
            if (tokenizer.checkToken("def")) {
                PROCDEF procDef = new PROCDEF();
                procDef.parse();
                definitions.add(procDef);
            } else if (tokenizer.checkToken("set") || tokenizer.checkToken("new") || tokenizer.checkToken("print")
                    || tokenizer.checkToken("call")) {
                STATEMENT s = STATEMENT.makeStatement(tokenizer);
                s.parse();
                statements.add(s);
            } else {
                throw new RuntimeException(
                        "Unknown input:" + tokenizer.getNext() + " expect procedure definition or statement");
            }
        }

    }

    @Override
    public Integer evaluate() {
        for (PROCDEF d : definitions) {
            d.evaluate();
        }
        for (STATEMENT s : statements){
            s.evaluate();
        }
        return null; // we only return a value for expressions (EXP); evaluation of programs/statements is via side-effects
    }
}
