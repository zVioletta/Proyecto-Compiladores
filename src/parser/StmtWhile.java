package parser;

import tools.*;

public class StmtWhile extends Statement {
    final Expression condition;
    final Statement body;

    StmtWhile(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
        if (ASDR.preAn.tipo.equals(TipoToken.WHILE)) {
            new Match(TipoToken.WHILE);
            new Match(TipoToken.LEFT_PAREN);
            condition = new Expression();
            new Match(TipoToken.RIGHT_PAREN);
            new Statement();
        }
    }
}
