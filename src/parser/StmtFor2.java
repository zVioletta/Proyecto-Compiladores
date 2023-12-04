package parser;

import tools.*;

public class StmtFor2 extends Expression{
    StmtFor2() {
        if(Expression()) {
            new Expression();
            new Match(TipoToken.SEMICOLON);
        } else {
            new Match(TipoToken.SEMICOLON);
        }
    }

}
