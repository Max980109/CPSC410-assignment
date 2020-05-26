package ast;

import libs.Node;
import libs.Tokenizer;

public abstract class STATEMENT extends Node {
    public static STATEMENT makeStatement(Tokenizer tokenizer) {
        if (tokenizer.checkToken("new")) {
            return new DEC();
        }
        else if (tokenizer.checkToken("set")) {
            return new SET();
        }
        else if (tokenizer.checkToken("print")) {
            return new PRINT();
        }
        else if (tokenizer.checkToken("call")) {
            return new PROCCALL();
        }
        else {
            throw new RuntimeException("Unknown value: " + tokenizer.getNext());
        }
    }
}
