package ast;

public class BOLDROW extends ROW {
    @Override
    public void evaluate() {
        writer.println("\t<tr>");

        // Evaluate Items
        for (ITEM item : items){
            writer.print("\t\t<td bgcolor=white><b>");
            item.evaluate();
            writer.println("</b></td>");
        }

        writer.println("\t</tr>");
    }

}
