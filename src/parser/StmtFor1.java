package parser;

import tools.*;

public class StmtFor1 extends Expression{
    Expression expression;

    StmtFor1() {
        if (DecVar()) {
            new DecVar();
        } else if (StmtExpression()) {
            new StmtExpression(expression);
        } else {
            new Match(TipoToken.SEMICOLON);
        }
    }
}