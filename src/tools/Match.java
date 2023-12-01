package tools;

import java.util.List;

public class Match {
    List<Token> tokens;

    Match (TipoToken tt) {
        ASDR.preAn = this.tokens.get(ASDR.i);
        if(ASDR.preAn.tipo == tt){
            ASDR.i++;
            ASDR.preAn = this.tokens.get(ASDR.i);
        }else{
            new Error();
        }
    }
}
