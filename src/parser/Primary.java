package parser;

import tools.ASDR;
import tools.Match;
import tools.TipoToken;
import tools.Error;

abstract class Primary extends Expression {

    public Primary () {
        if (ASDR.preAn.tipo.equals(TipoToken.TRUE)) {
            new Match(TipoToken.TRUE);
        } else if (ASDR.preAn.tipo.equals(TipoToken.FALSE)) {
            new Match(TipoToken.FALSE);
        } else if (ASDR.preAn.tipo.equals(TipoToken.NUMBER)) {
            new Match(TipoToken.NUMBER);
        } else if (ASDR.preAn.tipo.equals(TipoToken.NULL)) {
            new Match(TipoToken.NULL);
        } else if (ASDR.preAn.tipo.equals(TipoToken.STRING)) {
            new Match(TipoToken.STRING);
        } else if (ASDR.preAn.tipo.equals(TipoToken.IDENTIFIER)) {
            new Match(TipoToken.IDENTIFIER);
        } else if (ASDR.preAn.tipo.equals(TipoToken.LEFT_PAREN)) {
            new Match(TipoToken.LEFT_PAREN);
            if (Expression()) {
               ASDR.i++;
                if (ASDR.preAn.tipo.equals(TipoToken.RIGHT_PAREN)) {
                    new Match(TipoToken.RIGHT_PAREN);
                } else
                    new Error();
            } else
                new Error();
        }
    }
}
