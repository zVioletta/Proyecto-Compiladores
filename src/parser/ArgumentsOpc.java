package parser;

import tools.ASDR;
import tools.Error;

public class ArgumentsOpc extends Arguments{
    boolean ArgumentsOpc(){
        if (Expression()) {
            if (Arguments()) {
                new Arguments();
            } else {
                new Error();
            }
        }
        return false;
    }
}
