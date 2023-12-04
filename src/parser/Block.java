package parser;

import tools.ASDR;
import tools.Error;
import tools.TipoToken;

public class Block extends Statement{
    Declaration declaration;
    public boolean Block(Declaration declaration) {
        if (ASDR.preAn.tipo.equals(TipoToken.LEFT_BRACE)) {
            ASDR.i++;
            if (declaration != null) {
                if (ASDR.preAn.tipo.equals(TipoToken.RIGHT_BRACE)) {
                    ASDR.i++;
                    return true;
                } else {
                    new Error();
                }
            } else {
                new Error();
            }
        }
        return false;
    }
}
