package parser;

import tools.*;
import tools.Error;

import java.util.List;

public class Arguments extends Expression {

    boolean Arguments() {
        if (ASDR.preAn.tipo.equals(TipoToken.COMMA)) {
            new Match(TipoToken.COMMA);
            ASDR.i++;
            if (Expression()) {
                ASDR.i++;
                if (Arguments()) {
                    new Arguments();
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