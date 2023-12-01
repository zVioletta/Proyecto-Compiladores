package parser;

import tools.ASDR;
import tools.Error;

public class ArgumentsOpc extends Arguments{
    boolean ArgumentsOpc(){
        if (Expression()) {
            if (Arguments()) {
                return true;
            } else {
                new Error();
            }
        }
        return false;
    }
}
