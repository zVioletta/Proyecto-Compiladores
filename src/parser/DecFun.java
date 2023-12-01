package parser;

import tools.ASDR;
import tools.TipoToken;

import java.util.List;

public class DecFun extends Declaration {

    public DecFun(){
        if (ASDR.preAn.tipo.equals(TipoToken.FUN)) {
            ASDR.i++;
        }
    }
}