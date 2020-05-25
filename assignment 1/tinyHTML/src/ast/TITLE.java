package ast;

import libs.Node;

public class TITLE extends Node{
    String title;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("Title:");
        title=tokenizer.getNext();
    }

    @Override
    public void evaluate() {
        writer.println("<h1>" + title+"</h1>");
    }
}
