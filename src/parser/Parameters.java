package parser;

import tools.ASDR;
import tools.Error;
import tools.TipoToken;

abstract class Parameters extends Parameters2{

    public boolean Parameters() {
        if (ASDR.preAn.tipo.equals(TipoToken.IDENTIFIER)) {
            ASDR.i++;
            if (Parameters2()) {
                return true;
            } else {
                new Error();
            }
        }
        return false;
    }
}
