package parser;

import tools.ASDR;
import tools.Error;
import tools.Match;
import tools.TipoToken;

public class Parameters2 {

    public boolean Parameters2(){
        if (ASDR.preAn.tipo.equals(TipoToken.COMMA)) {
            new Match(TipoToken.COMMA);
            if (ASDR.preAn.tipo.equals(TipoToken.IDENTIFIER)) {
                new Match(TipoToken.IDENTIFIER);
                if (Parameters2()) {
                    new Parameters2();
                } else {
                    new Error();
                }
            } else {
                new Error();
            }
        } else {
            new Match(TipoToken.EOF);
        }
        return false;
    }
}
