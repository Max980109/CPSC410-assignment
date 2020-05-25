package ast;

import jdk.internal.jimage.ImageReader.Node;
import libs.Tokenizer;
import sun.security.pkcs11.wrapper.CK_TOKEN_INFO;

public class Shape {
    private String base;
    private Shape inside = new Shape();
    public void parse() {
        if (tokenizer.checkToken("Triangle") == true) {
            tokenizer.getNext();
            base = "Triangle";
        }
        if (tokenizer.checkToken("Circle") == true) {
            tokenizer.getNext();
            base = "Circle";
        }
        tokenizer.getAndCheckNext("With");
        while(tokenizer.moreTokens() && !tokenizer.checkToken("Inside")) {
            inside.parse();
        }
        tokenizer.getAndCheckNext("Inside");
    }
}