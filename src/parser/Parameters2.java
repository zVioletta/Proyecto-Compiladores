package parser;

import tools.ASDR;
import tools.Error;
import tools.TipoToken;

public class Parameters2 {

    public boolean Parameters2(){
        if (ASDR.preAn.tipo.equals(TipoToken.COMMA)) {
            ASDR.i++;
            if (ASDR.preAn.tipo.equals(TipoToken.IDENTIFIER)) {
                ASDR.i++;
                if (Parameters2()) {
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
