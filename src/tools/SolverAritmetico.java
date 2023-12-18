package tools;

public class SolverAritmetico {
    private final Nodo nodo;

    public SolverAritmetico(Nodo nodo) {
        this.nodo = nodo;
    }

    public Object resolver(TablaSimbolos ts) {
        return resolver(nodo, ts);
    }

    private Object resolver(Nodo n, TablaSimbolos ts) {
        // No tiene hijos, es un operando
        if (n.getHijos() == null) {
            if (n.getValue().tipo == TipoToken.NUMBER || n.getValue().tipo == TipoToken.STRING) {
                return n.getValue().literal;
            } else if (n.getValue().tipo == TipoToken.IDENTIFIER) {
                // Ver la tabla de símbolos
                return ts.obtener(n.getValue().lexema);
            }
        }

        Nodo izq = n.getHijos().get(0);
        Nodo der = n.getHijos().get(1);

        Object resultadoIzquierdo = resolver(izq, ts);
        Object resultadoDerecho = resolver(der, ts);

        if (resultadoIzquierdo instanceof Double && resultadoDerecho instanceof Double) {
            switch (n.getValue().tipo) {
                case PLUS:
                    return ((Double) resultadoIzquierdo + (Double) resultadoDerecho);
                case MINUS:
                    return ((Double) resultadoIzquierdo - (Double) resultadoDerecho);
                case STAR:
                    return ((Double) resultadoIzquierdo * (Double) resultadoDerecho);
                case SLASH:
                    return ((Double) resultadoIzquierdo / (Double) resultadoDerecho);
            }
        } else if (resultadoIzquierdo instanceof String && resultadoDerecho instanceof String) {

            if (n.getValue().tipo == TipoToken.PLUS) {
                // Ejecutar la concatenación
                return ((String) resultadoIzquierdo + (String) resultadoDerecho);

            }
        } else {
            // Error por diferencia de tipos
            System.out.println("Error, tipos incompatibles");
            System.exit(0);
        }

        return null;
    }
}
