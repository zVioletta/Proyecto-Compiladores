package parser;

import tools.*;

public class StmtElse extends Statement{
    final Statement elseBranch;

    StmtElse(Statement elseBranch){
        this.elseBranch = elseBranch;
        if (ASDR.preAn.tipo.equals(TipoToken.ELSE)) {
            new Match(TipoToken.ELSE);
            new Statement();
        }
    }
}
