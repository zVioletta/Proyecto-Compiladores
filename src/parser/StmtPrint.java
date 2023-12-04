package parser;

import tools.*;

public class StmtPrint extends Statement {
    final Expression expression;

    StmtPrint(Expression expression) {
        this.expression = expression;
        if (ASDR.preAn.tipo.equals(TipoToken.PRINT)) {
            new Match(TipoToken.PRINT);
            expression = new Expression();
            new Match(TipoToken.SEMICOLON);
        }
    }
}
