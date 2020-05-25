package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.List;

public class TABLE extends Node {
    List<ROW> rows = new ArrayList<>();
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("Table:");

        // Parse BoldRow
        BOLDROW boldRow = new BOLDROW();
        boldRow.parse();
        rows.add(boldRow);

        // Parse Rows
        while (tokenizer.moreTokens() && !tokenizer.checkToken("Table:")){
            ROW row = new ROW();
            row.parse();
            rows.add(row);
        }
    }

    @Override
    public void evaluate() {
        writer.println("<table bgcolor=black width=600>");
        for (ROW r : rows){
            r.evaluate();
        }
        writer.println("</table>");
    }
}
