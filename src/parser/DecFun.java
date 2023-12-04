package parser;

import tools.ASDR;
import tools.Match;
import tools.TipoToken;

import java.util.List;

public class DecFun extends Declaration {

    public DecFun(){
        if (ASDR.preAn.tipo.equals(TipoToken.FUN)) {
            new Match(TipoToken.FUN);
            new Function();
        }
    }
}