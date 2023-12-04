package parser;

import tools.ASDR;
import tools.Match;
import tools.TipoToken;
import tools.Token;

import java.util.List;

public class Function extends ParametersOpc {

    public boolean Function(List<Token> tokens) {
        if (ASDR.preAn.tipo.equals(TipoToken.IDENTIFIER)) {
            new Match(TipoToken.IDENTIFIER);
            if (ASDR.preAn.tipo.equals(TipoToken.LEFT_PAREN)) {
                new Match(TipoToken.LEFT_PAREN);
                if (ParametersOpc()) {
                    ParametersOpc();
                    if (ASDR.preAn.tipo == TipoToken.RIGHT_PAREN) {
                        new Match(TipoToken.RIGHT_PAREN);
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
