package ast;

import java.util.ArrayList;
import java.util.List;

public class PROCBODY extends EXP {
    private List<STATEMENT> statements = new ArrayList<>();

    @Override
    public void parse() {
       tokenizer.getAndCheckNext("\\{");
       while (!tokenizer.checkToken("\\}")) {
            STATEMENT s;
            s = STATEMENT.makeStatement(tokenizer);
            s.parse();
            statements.add(s);
       }
       tokenizer.getAndCheckNext("\\}");

    }

    @Override
    public Integer evaluate() {
       for (STATEMENT s : statements) {
           s.evaluate();
       }
       return null;
    }
    
    
}