package parser;

import tools.Token;

import java.util.List;

public class ExprCallFunction extends Expression{
    final Expression callee;
    // final tools.Token paren;
    final List<Expression> arguments;

    ExprCallFunction(Expression callee, /*tools.Token paren,*/ List<Expression> arguments) {
        this.callee = callee;
        // this.paren = paren;
        this.arguments = arguments;
    }
}
