package parser;

import tools.ASDR;
import tools.TipoToken;
import tools.Token;

import java.util.List;

public class Function extends ParametersOpc {

    public boolean Function(List<Token> tokens) {
        if (ASDR.preAn.tipo.equals(TipoToken.IDENTIFIER)) {
            ASDR.i++;
            if (ASDR.preAn.tipo.equals(TipoToken.LEFT_PAREN)) {
                ASDR.i++;
                if (ParametersOpc()) {
                    ASDR.i++;
                    if (ASDR.preAn.tipo == TipoToken.RIGHT_PAREN) {
                        ASDR.i++;
                        if (Block()) {
                            return true;
                        } else {
                            new Error();
                        }
                    } else {
                        new Error();
                    }
                } else {
                    new Error();
                }
            }
        }
    }
}
