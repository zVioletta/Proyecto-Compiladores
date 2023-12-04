package parser;

import tools.*;

public class Functions extends DecFun {

    public boolean Functions(){
        if (DecFun()) {
            ASDR.i++;
            Functions();
        } else {
            new Match(TipoToken.EOF);
        }
        return false;
    }
}
