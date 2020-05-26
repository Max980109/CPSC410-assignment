package ui;

import ast.PROGRAM;
import libs.Tokenizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static final Map<String, Object> symbolTable = new HashMap<>(); // all values are integers

    public static void main(String[] args) {

        List<String> literals = Arrays.asList("def", "set", "print", "new", ",", "call", "print", "{", "}", "return", "(", ")");
        Tokenizer.makeTokenizer("input.tvar",literals);
        PROGRAM p = new PROGRAM();
        p.parse();
        p.evaluate(Main.symbolTable);
        System.out.println("completed successfully");
        System.out.println(symbolTable);
    }

}
