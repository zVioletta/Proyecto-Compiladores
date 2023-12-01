package parser;

import tools.ASDR;
import tools.Error;
import tools.TipoToken;

public class Block extends Statement{
    public boolean Block(){
        if (ASDR.preAn.tipo.equals(TipoToken.LEFT_BRACE)) {
            ASDR.i++;
            if (Declaration()) {
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
