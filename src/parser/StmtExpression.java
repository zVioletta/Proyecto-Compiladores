package parser;

import tools.*;

public class StmtExpression extends Statement {
    final Expression expression;

    StmtExpression(Expression expression) {
        this.expression = expression;
        if (expression) {
            new Match(TipoToken.SEMICOLON);
        }
    }
}
