package parser;

import tools.*;

public class StmtIf extends Statement {
    final Expression condition;
    final Statement thenBranch;
    final Statement elseBranch;

    StmtIf(Expression condition, Statement thenBranch, Statement elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
        if (ASDR.preAn.tipo.equals(TipoToken.IF)) {
            new Match(TipoToken.IF);
            new Match(TipoToken.LEFT_PAREN);
            condition = new Expression();
            new Match(TipoToken.RIGHT_PAREN);
            new StmtElse(elseBranch);

        }
    }
}