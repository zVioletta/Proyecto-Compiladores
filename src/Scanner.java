import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TipoToken> palabrasReservadas;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and",    TipoToken.AND);
        palabrasReservadas.put("else",   TipoToken.ELSE);
        palabrasReservadas.put("false",  TipoToken.FALSE);
        palabrasReservadas.put("for",    TipoToken.FOR);
        palabrasReservadas.put("fun",    TipoToken.FUN);
        palabrasReservadas.put("if",     TipoToken.IF);
        palabrasReservadas.put("null",   TipoToken.NULL);
        palabrasReservadas.put("or",     TipoToken.OR);
        palabrasReservadas.put("print",  TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true",   TipoToken.TRUE);
        palabrasReservadas.put("var",    TipoToken.VAR);
        palabrasReservadas.put("while",  TipoToken.WHILE);
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();
    
    public Scanner(String source){
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {
        int estado = 0;
        String lexema = "";
        char c;

        for(int i=0; i<source.length(); i++){
            c = source.charAt(i);

            switch (estado){
                case 0:
                    if(Character.isLetter(c)){
                        estado = 13;
                        lexema += c;
                    }
                    else if(Character.isDigit(c)){
                        estado = 15;
                        lexema += c;
                        /*
                        while(Character.isDigit(c)){
                            lexema += c;
                            i++;
                            c = source.charAt(i);
                        }
                        Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
                        lexema = "";
                        estado = 0;
                        tokens.add(t);
                        */
                    }
                    else if (c == '>') {
                        estado = 1;
                        lexema += c;
                    }
                    else if (c == '<') {
                        estado = 4;
                        lexema += c;
                    }
                    else if (c == '=') {
                        estado = 7;
                        lexema += c;
                    }
                    else if (c == '!') {
                        estado = 10;
                        lexema += c;
                    }
                    break;

                case 1:
                    if ('=' == source.charAt(i + 1)) {
                        estado = 2;
                        lexema += c;
                    }
                    else {
                        Token t = new Token(TipoToken.GREATER, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 2:
                    if (true) {
                        Token t = new Token(TipoToken.GREATER_EQUAL, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 4:
                    if (source.charAt(i + 1) == '=') {
                        estado = 5;
                        lexema += c;
                    }
                    else {
                        Token t = new Token(TipoToken.LESS, lexema);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 5:
                    tokens.add(new Token(TipoToken.LESS_EQUAL, lexema));
                    estado = 0;
                    lexema = "";
                    i--;
                    break;

                case 7:
                    if (source.charAt(i + 1) == '=') {
                        estado = 8;
                        lexema += c;
                    }
                    else {
                        Token t = new Token(TipoToken.EQUAL, lexema);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 8:
                    tokens.add(new Token(TipoToken.EQUAL_EQUAL, lexema));
                    estado = 0;
                    lexema = "";
                    i--;
                    break;

                case 10:
                    if (source.charAt(i + 1) == '=') {
                        estado = 11;
                        lexema += c;
                    }
                    else {
                        Token t = new Token(TipoToken.BANG, lexema);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 11:
                    tokens.add(new Token(TipoToken.BANG_EQUAL, lexema));
                    estado = 0;
                    lexema = "";
                    i--;
                    break;

                case 13:
                    if (Character.isLetterOrDigit(c)) {
                        estado = 13;
                        lexema += c;
                    }
                    else {
                        TipoToken tt = palabrasReservadas.get(lexema);
                        if (tt == null) {
                            Token t = new Token(TipoToken.IDENTIFIER, lexema);
                            tokens.add(t);
                        }
                        else {
                            Token t = new Token(tt, lexema);
                            tokens.add(t);
                        }
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 15:
                    if (Character.isDigit(c)) {
                        estado = 15;
                        lexema += c;
                    }
                    else if (c == '.') {
                        estado = 16;
                        lexema += c;
                    }
                    else if (c == 'E') {
                        estado = 18;
                        lexema += c;
                    }
                    else{ // Case 22
                        Token t = new Token(TipoToken.NUMBER, lexema, parseLiteral(lexema));
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 16:
                    if (Character.isDigit(c)) {
                        estado = 17;
                        lexema += c;
                    } else {
                        Token t = new Token(TipoToken.DOT, lexema);
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                        i--;
                    }
                    break;

                case 17:
                    if (Character.isDigit(c)) {
                        estado = 17;
                        lexema += c;
                    } else if (c == 'E') {
                        estado = 18;
                        lexema += c;
                    } else { // Case 23
                        Token t = new Token(TipoToken.NUMBER, lexema, parseLiteral(lexema));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                        i--;
                    }
                    break;

                case 18:
                    if (c == '+' || c == '-') {
                        estado = 19;
                        lexema += c;
                    } else if (Character.isDigit(c)) {
                        estado = 20;
                        lexema += c;
                    }
                    break;

                case 19:
                    if (Character.isDigit(c)) {
                        estado = 17;
                        lexema += c;
                    }
                    break;

                case 20:
                    if (Character.isDigit(c)) {
                        estado = 20;
                        lexema += c;
                    } else { // Case 21
                        Token t = new Token(TipoToken.NUMBER, lexema, parseLiteral(lexema));
                        lexema = "";
                        estado = 0;
                        i--;
                    }
                    break;
            }
        }
        return tokens;
    }

    private Object parseLiteral(String lexema) {
        try {
            if (lexema.contains(".")) {
                return Double.valueOf(lexema);
            } else {
                return Integer.valueOf(lexema);
            }
        } catch (NumberFormatException e) {
            return lexema; // Si no es un número válido, retornar como cadena
        }
    }
}
