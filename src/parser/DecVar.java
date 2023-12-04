package parser;

import tools.*;
import tools.Error;

public class DecVar extends Declaration {
    public boolean DecVar() {
        if (ASDR.preAn.tipo.equals(TipoToken.VAR)) {
            new Match(TipoToken.VAR);
            if (ASDR.preAn.tipo.equals(TipoToken.IDENTIFIER)) {
                new Match(TipoToken.IDENTIFIER);
                if (VarInit()) {
                    ASDR.i++;
                    new Match(TipoToken.SEMICOLON);
                } else {
                    new Error();
                }
            } else {
                new Error();
            }
        return false;
    }
}
