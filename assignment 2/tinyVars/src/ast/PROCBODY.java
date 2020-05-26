package ast;

import java.util.ArrayList;
import java.util.List;

public class PROCBODY extends EXP {
    private List<STATEMENT> statements = new ArrayList<>();
    private EXP ret;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("\\{");
        while (!tokenizer.checkToken("\\}") && !tokenizer.checkToken("return")) {
                STATEMENT s;
                s = STATEMENT.makeStatement(tokenizer);
                s.parse();
                statements.add(s);
        }
        if (tokenizer.checkToken("return")) {
            tokenizer.getNext();
            ret = EXP.makeExp(tokenizer);
            ret.parse();
        }
        tokenizer.getAndCheckNext("\\}");
    }

    @Override
    public Integer evaluate() {
        for (STATEMENT s : statements) {
            s.evaluate();
        }
        
        if (ret != null) {
            Integer integer = ret.evaluate();
            return integer;
        } else {
            return null;
        }

    }
}