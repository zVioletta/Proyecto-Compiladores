package tools;


import java.util.List;

public class Error {
    String message = "Error on: " + (ASDR.i + 1) + ". Expecting:" + ASDR.tokens.get(ASDR.i);

    public Error(){
        System.out.println(message);
    }
}