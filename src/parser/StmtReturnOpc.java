package parser;

import tools.*;

public class StmtReturnOpc extends Expression{
    final Expression value;

    StmtReturnOpc(){
        this.value = new Expression();
        if (ASDR.preAn.tipo.equals(TipoToken.SEMICOLON)){
            new Match(TipoToken.SEMICOLON);
        }
    }
}
