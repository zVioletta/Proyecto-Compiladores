package parser;

import tools.Token;

public class ExprSuper extends Expression {
    // final tools.Token keyword;
    final Token method;

    ExprSuper(Token method) {
        // this.keyword = keyword;
        this.method = method;
    }
}