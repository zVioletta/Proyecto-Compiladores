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

     /* public Token(TipoToken tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = null;
    } */

    public boolean equals(Object o) {
        if (!(o instanceof Token)) {
            return false;
        }

        if(this.tipo == ((Token)o).tipo){
            return true;
        }

        return false;
    }


    public boolean esOperando(){
        switch (this.tipo){
            case IDENTIFIER:
            case NUMBER:
            case STRING:
                return true;
            default:
                return false;
        }
    }

    public boolean esOperador(){
        switch (this.tipo){
            case PLUS:
            case MINUS:
            case STAR:
            case SLASH:
            case EQUAL:
            case GREATER:
            case GREATER_EQUAL:
            case LESS:
            case LESS_EQUAL:
            case EQUAL_EQUAL:
            case AND:
            case OR:
                return true;
            default:
                return false;
        }
    }

    public boolean esPalabraReservada(){
        switch (this.tipo){
            case VAR:
            case IF:
            case PRINT:
            case ELSE:
            case RETURN:
            case NULL:
            case FALSE:
            case TRUE:
            case WHILE:
            case FOR:
                return true;
            default:
                return false;
        }
    }

    public boolean esEstructuraDeControl(){
        switch (this.tipo){
            case IF:
            case ELSE:
            case WHILE:
            case FOR:
                return true;
            default:
                return false;
        }
    }

    public boolean precedenciaMayorIgual(Token t){
        return this.obtenerPrecedencia() >= t.obtenerPrecedencia();
    }

    private int obtenerPrecedencia(){
        switch (this.tipo){
            case STAR:
            case SLASH:
                return 7;
            case PLUS:
            case MINUS:
                return 6;
            case EQUAL:
                return 1;
            case AND:
                return 3;
            case OR:
                return 2;
            case EQUAL_EQUAL:
                return 4;
            case GREATER:
            case GREATER_EQUAL:
            case LESS:
            case LESS_EQUAL:
                return 4;
        }

        return 0;
    }

    public int aridad(){
        switch (this.tipo) {
            case STAR:
            case SLASH:
            case PLUS:
            case MINUS:
            case EQUAL:
            case GREATER:
            case GREATER_EQUAL:
            case LESS_EQUAL:
            case LESS:
            case AND:
            case OR:
                return 2;
        }
        return 0;
    }

    public String toString() {
        return "<" + tipo + " " + lexema + " " + literal + ">";
    }
}
