package ast;
import ui.Main;

public class PROCCALLEXP extends EXP {


  private String name;

    @Override
    public void parse() {
       tokenizer.getAndCheckNext("call");
       name = tokenizer.getNext();

    }

    @Override
    public Integer evaluate() {
        PROCBODY proBODY = (PROCBODY) Main.symbolTable.get(name);
        return proBODY.evaluate();
    }
    
}