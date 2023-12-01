package parser;

import tools.ASDR;
import tools.Token;
import tools.TipoToken;
import tools.Error;

import java.util.List;

abstract class Arguments extends Expression {
    int i;
    Token preAn;
    List<Token> tokens;

    boolean Arguments() {
        preAn = this.tokens.get(i);
        if (preAn.tipo.equals(TipoToken.COMMA)) {
            i++;
            if (Expression()) {
                if (Arguments()) {
                    return true;
                } else {
                    new Error();
                }
            } else {
                new Error();
            }
        } else {
            return true;
        }
        return false;
    }
}