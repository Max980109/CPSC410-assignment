package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.List;

public class ROW extends Node{
    List<ITEM> items = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("\\[");

        // Parse first Item
        ITEM firstItem = new ITEM();
        firstItem.parse();
        items.add(firstItem);

        // Parse Items
        while(!tokenizer.checkToken("\\]")){
            tokenizer.getAndCheckNext("\\|");
            ITEM item = new ITEM();
            item.parse();
            items.add(item);
        }

        tokenizer.getAndCheckNext("\\]");
    }

    @Override
    public void evaluate() {
        writer.println("\t<tr>");

        // Evaluate Items
        for (ITEM item : items) {
            writer.print("\t\t<td bgcolor=white>");
            item.evaluate();
            writer.println("</td>");
        }

        writer.println("\t</tr>");
    }
}
