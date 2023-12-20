package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private final String source;
    private int pActual;
    private final List<Token> tokens = new ArrayList<>();

    private static final Map<String, TipoToken> palabrasReservadas;
    static {
        palabrasReservadas = new HashMap<>();

        palabrasReservadas.put("var", TipoToken.VAR);
        palabrasReservadas.put(")", TipoToken.RIGHT_PAREN);
        palabrasReservadas.put("(", TipoToken.LEFT_PAREN);
        palabrasReservadas.put("}", TipoToken.RIGHT_BRACE);
        palabrasReservadas.put("{", TipoToken.LEFT_BRACE);
        palabrasReservadas.put(",", TipoToken.COMMA);
        palabrasReservadas.put(".", TipoToken.DOT);
        palabrasReservadas.put(";", TipoToken.SEMICOLON);
        palabrasReservadas.put("-", TipoToken.MINUS);
        palabrasReservadas.put("+", TipoToken.PLUS);
        palabrasReservadas.put("*", TipoToken.STAR);
        palabrasReservadas.put("/", TipoToken.SLASH);
        palabrasReservadas.put("!", TipoToken.BANG);
        palabrasReservadas.put("!=", TipoToken.BANG_EQUAL);
        palabrasReservadas.put("=", TipoToken.EQUAL);
        palabrasReservadas.put("==", TipoToken.EQUAL_EQUAL);
        palabrasReservadas.put("<", TipoToken.LESS);
        palabrasReservadas.put("<=", TipoToken.LESS_EQUAL);
        palabrasReservadas.put(">", TipoToken.GREATER);
        palabrasReservadas.put(">=", TipoToken.GREATER_EQUAL);
        palabrasReservadas.put("if", TipoToken.IF);
        palabrasReservadas.put("print", TipoToken.PRINT);
        palabrasReservadas.put("for", TipoToken.FOR);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("while", TipoToken.WHILE);
        palabrasReservadas.put("fun", TipoToken.FUN);
        palabrasReservadas.put("else", TipoToken.ELSE);
        palabrasReservadas.put("and", TipoToken.AND);
        palabrasReservadas.put("or", TipoToken.OR);
    }

    Scanner(String source) {
        this.source = source + " ";
        this.pActual = 0;
    }

    List<Token> scanTokens() {
        while (pActual < source.length()) {
            char cActual = source.charAt(pActual);

            if (esCaracterIgnorable(cActual)) {
                // Ignorar el caracter y avanzar a la siguiente posición
                pActual++;
            } else if (esInicioDePalabra(cActual)) {
                // Escanear la palabra
                String palabra = escanearPalabra();
                tokens.add(crearToken(palabra));
            } else if (esNumero(cActual)) {
                String numero = escanearNumero();
                tokens.add(new Token(TipoToken.NUMBER, numero, Double.valueOf(numero), pActual));
            } else if (cActual == '"') {
                // Escanear cadena entre comillas
                String cadena = escanearCadena();
                tokens.add(new Token(TipoToken.STRING, cadena, cadena, pActual));
            } else if (cActual == '/') {
                if (source.charAt(pActual + 1) == '/') {
                    pActual++;
                    while (source.charAt(pActual) != '\n') {
                        pActual++;
                        if (source.charAt(pActual) == '\0') {
                            pActual++;
                            break;
                        }
                    }
                }
                else if (source.charAt(pActual + 1) == '*') {
                    pActual++;
                    while (true) {
                        pActual++;
                        // System.out.println(source.charAt(pActual));
                        if (source.charAt(pActual) == '*' && source.charAt(pActual + 1) == '/') {
                            if (source.charAt(pActual + 2) == '\0') {
                                pActual++;
                                break;
                            } else {
                                pActual = pActual + 2;
                                break;
                            }
                        }
                    }
                }
            } else {
                // Tratar el caracter como un carácter no reservado
                tokens.add(crearToken(String.valueOf(cActual)));
                pActual++;
            }
        }
        tokens.add(new Token(TipoToken.EOF, "", null, pActual));
        return tokens;
    }

    private boolean esCaracterIgnorable(char c) {
        return c == ' ' || c == '\t' || c == '\n';
    }

    private boolean esInicioDePalabra(char c) {
        return Character.isLetter(c);
    }

    private boolean esNumero(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private String escanearPalabra() {
        int inicio = pActual;
        while (pActual < source.length() && Character.isLetter(source.charAt(pActual))) {
            pActual++;
        }
        while (pActual < source.length() && Character.isDigit(source.charAt(pActual))) {
            pActual++;
        }
        return source.substring(inicio, pActual);
    }

    private String escanearCadena() {
        int inicio = pActual + 1; // Ignorar el primer "
        pActual++; // Avanzar a la siguiente posición

        while (pActual < source.length() && source.charAt(pActual) != '"') {
            pActual++;
        }

        String cadena = source.substring(inicio, pActual);
        pActual++; // Avanzar a la posición después del último "

        return cadena;
    }

    private Token crearToken(String lexema) {
        if (palabrasReservadas.containsKey(lexema)) {
            TipoToken tipo = palabrasReservadas.get(lexema);
            return new Token(tipo, lexema, null, pActual);
        } else if (isNumeric(lexema)) {
            return new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema), pActual);
        } else if (lexema.equals("fun")) {
            return new Token(TipoToken.FUN, lexema, null, pActual);
        } else {
            return new Token(TipoToken.IDENTIFIER, lexema, null, pActual);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            // El valor no es un número entero
        }

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            // El valor no es un número decimal
        }

        return false;
    }

    private String escanearNumero() {
        int inicio = pActual;
        boolean hayPunto = false;

        while (pActual < source.length()
                && (Character.isDigit(source.charAt(pActual)) || source.charAt(pActual) == '.')) {
            if (source.charAt(pActual) == '.') {
                if (hayPunto) {
                    // Ya hay un punto en el número, lo tratamos como una palabra
                    return escanearPalabra();
                }
                hayPunto = true;
            }
            pActual++;
        }

        return source.substring(inicio, pActual);
    }
}
