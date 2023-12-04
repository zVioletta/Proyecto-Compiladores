package parser;

import tools.*;
import tools.Error;

public class StmtFor extends Statement {
    StmtFor1 stmtFor1;
    StmtFor2 stmtFor2;
    StmtFor3 stmtFor3;

     StmtFor() {
        if (ASDR.preAn.tipo.equals(TipoToken.FOR)) {
            new Match(TipoToken.FOR);
            if (ASDR.preAn.tipo.equals(TipoToken.LEFT_PAREN)) {
                new Match(TipoToken.LEFT_PAREN);
                stmtFor1 = new StmtFor1();
                stmtFor2 = new StmtFor2();
                stmtFor3 = new StmtFor3();
                if (ASDR.preAn.tipo.equals(TipoToken.RIGHT_PAREN)) {
                    new Match(TipoToken.RIGHT_PAREN);
                    new Statement();
                } else {
                    new Error();
                }
            } else {
                new Error();
            }
        }
    }
}
