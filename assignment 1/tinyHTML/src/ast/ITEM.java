package ast;

import libs.Node;

public class ITEM extends Node {
    String itemName;

    @Override
    public void parse() {
        itemName = tokenizer.getNext();
    }

    @Override
    public void evaluate() {
        writer.print(itemName);
    }
}
