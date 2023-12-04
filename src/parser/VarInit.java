package parser;

import tools.*;
import tools.Error;

public class VarInit extends Declaration {
    public boolean VarInit() {
        if (ASDR.preAn.tipo.equals(TipoToken.EQUAL)) {
            new Match(TipoToken.EQUAL);
            new Expression();
        } else {
            return true;
        }
        return false;
    }
}
