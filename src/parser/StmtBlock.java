package parser;

import tools.*;
import tools.Error;

import java.util.List;

public class StmtBlock extends Statement{
    final List<Statement> statements;

    StmtBlock(List<Statement> statements) {
        this.statements = statements;
        if (ASDR.preAn.tipo.equals(TipoToken.LEFT_BRACE)) {
            ASDR.i++;
            if (statements != null) {
                if (ASDR.preAn.tipo.equals(TipoToken.RIGHT_BRACE)) {
                    ASDR.i++;
                } else {
                    new Error();
                }
            } else {
                new Error();
            }
        }
    }
}