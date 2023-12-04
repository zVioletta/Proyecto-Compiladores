package parser;

import tools.*;

public class ParametersOpc extends Parameters{
    public boolean ParametersOpc(){
        if (Parameters()) {
            new Parameters();
        } else {
            new Match(TipoToken.EOF);
        }
        return false;
    }
}
