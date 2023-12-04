package parser;

import tools.*;

public class StmtReturn extends Statement {
    final Expression value;

    StmtReturn(Expression value) {
        this.value = value;
        if (ASDR.preAn.tipo.equals(TipoToken.RETURN)) {
            new Match(TipoToken.RETURN);
            value = new Expression();
        }
    }
}
