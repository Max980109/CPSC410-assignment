package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PROCBODY extends EXP {
    private List<STATEMENT> statements = new ArrayList<>();
    private EXP ret;
    private List<Integer> result_integer = new ArrayList<>();
    private List<NAME> names = new ArrayList<>();
    Map<String, Object> local_table =  new HashMap<>();

    public void set_exp_integers(List<Integer> exp_integers) {
        result_integer = exp_integers;
	}

    @Override
    public void parse() {
        if (tokenizer.checkToken("\\(")) {
            tokenizer.getNext();
            while (!tokenizer.checkToken("\\)")) {
                NAME arg_1 = (NAME) EXP.makeExp(tokenizer);
                arg_1.parse();
                names.add(arg_1);
                while(tokenizer.checkToken(",")) {
                    tokenizer.getAndCheckNext(",");
                    NAME arg_more = (NAME) EXP.makeExp(tokenizer);
                    arg_more.parse();
                    names.add(arg_more);
                }
                tokenizer.getAndCheckNext("\\)");
                break;
            }
        }
        
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
    public Integer evaluate(Map<String, Object> symbolTable) {
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i).toString();
            Integer result = result_integer.get(i);
            local_table.put(name, result);
        }

        for (STATEMENT s : statements) {
            s.evaluate(local_table);
        }
        
        if (ret != null) {
            Integer integer = ret.evaluate(local_table);
            return integer;
        } else {
            return null;
        }
    }
}