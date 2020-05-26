package ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PROCCALL extends STATEMENT {
    private String name;
    private List<EXP> exps = new ArrayList<>();
    //( "(" NAME ("," NAME)* ")" )? "{" STATEMENT* "}"
    private List<Integer> exp_integers = new ArrayList<>();

    @Override
    public void parse() {
       tokenizer.getAndCheckNext("call");
       name = tokenizer.getNext();
       if (tokenizer.checkToken("\\(")) {
           tokenizer.getAndCheckNext("\\(");
           while (!tokenizer.checkToken("\\)")) {
               EXP exp = EXP.makeExp(tokenizer);
               exp.parse();
               exps.add(exp);
               while (tokenizer.checkToken(",")) {
                   tokenizer.getAndCheckNext(",");
                   EXP exp_more = EXP.makeExp(tokenizer);
                   exp_more.parse();
                   exps.add(exp_more);
               }
           }
           tokenizer.getAndCheckNext("\\)");
       }

    }

    @Override
    public Integer evaluate(Map<String, Object> symbolTable) {
        for (EXP exp: exps) {
            Integer integer = exp.evaluate(symbolTable);
            exp_integers.add(integer);
        }
        PROCBODY proBODY = (PROCBODY) symbolTable.get(name);
        proBODY.set_exp_integers(exp_integers);
        proBODY.evaluate(symbolTable);
        return null;
    }
    
}