package parser;

import tools.*;
import tools.Error;

public class Parameters extends Parameters2{

    public boolean Parameters() {
        if (ASDR.preAn.tipo.equals(TipoToken.IDENTIFIER)) {
            new Match(TipoToken.IDENTIFIER);
            if (Parameters2()) {
                Parameters2();
            } else {
                new Error();
            }
        }
        return false;
    }
}
