package tools;

public class Token {
    public final TipoToken tipo;
    final String lexema;
    final int posicion;
    final Object literal;

     /* public Token(TipoToken tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = null;
    }

    public Token(TipoToken tipo, String lexema, Object literal) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
    } */

    public Token(TipoToken tipo, String lexema, Object literal, int posicion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
        this.posicion = posicion;
    }

    public String toString() {
        return "<" + tipo + " " + lexema + " " + literal + ">";
    }
}
