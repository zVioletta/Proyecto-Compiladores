package tools;

public class Token {
    public final TipoToken tipo;
    final String lexema;
    final int posicion;
    final Object literal;

    public Token(TipoToken tipo, String lexema, Object literal, int posicion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
        this.posicion = posicion;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Token)) {
            return false;
        }

        if (this.tipo == ((Token) o).tipo) {
            return true;
        }

        return false;
    }

    public boolean esOperando() {
        return switch (this.tipo) {
            case IDENTIFIER, NUMBER, STRING -> true;
            default -> false;
        };
    }

    public boolean esOperador() {
        return switch (this.tipo) {
            case PLUS, MINUS, STAR, SLASH, EQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL, EQUAL_EQUAL, AND, OR ->
                    true;
            default -> false;
        };
    }

    public boolean esPalabraReservada() {
        return switch (this.tipo) {
            case VAR, IF, PRINT, ELSE, RETURN, NULL, FALSE, TRUE, WHILE, FOR -> true;
            default -> false;
        };
    }

    public boolean esEstructuraDeControl() {
        return switch (this.tipo) {
            case IF, ELSE, WHILE, FOR -> true;
            default -> false;
        };
    }

    public boolean precedenciaMayorIgual(Token t) {
        return this.obtenerPrecedencia() >= t.obtenerPrecedencia();
    }

    private int obtenerPrecedencia() {
        return switch (this.tipo) {
            case STAR, SLASH -> 7;
            case PLUS, MINUS -> 6;
            case EQUAL -> 1;
            case AND -> 3;
            case OR -> 2;
            case EQUAL_EQUAL -> 4;
            case GREATER, GREATER_EQUAL, LESS, LESS_EQUAL -> 4;
            default -> 0;
        };

    }

    public int aridad() {
        return switch (this.tipo) {
            case STAR, SLASH, PLUS, MINUS, EQUAL, GREATER, GREATER_EQUAL, LESS_EQUAL, LESS, AND, OR -> 2;
            default -> 0;
        };
    }

    public String toString() {
        return "<" + tipo + " " + lexema + " " + literal + ">";
    }
}
