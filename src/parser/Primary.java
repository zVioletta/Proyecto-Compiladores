package parser;

import tools.ASDR;
import tools.TipoToken;
import tools.Error;

abstract class Primary extends Expression {

    public Primary () {
        if (ASDR.preAn.tipo.equals(TipoToken.TRUE) || ASDR.preAn.tipo.equals(TipoToken.FALSE) || ASDR.preAn.tipo.equals(TipoToken.NUMBER) || ASDR.preAn.tipo.equals(TipoToken.NULL) || ASDR.preAn.tipo.equals(TipoToken.STRING) || ASDR.preAn.tipo.equals(TipoToken.IDENTIFIER))
            ASDR.i++;
        else if (ASDR.preAn.tipo.equals(TipoToken.LEFT_PAREN)) {
            ASDR.i++;
            if (Expression()) {
                ASDR.i++;
                if (ASDR.preAn.tipo.equals(TipoToken.RIGHT_PAREN))
                    ASDR.i++;
                else
                    new Error();
            } else
                new Error();
        }
    }
}
