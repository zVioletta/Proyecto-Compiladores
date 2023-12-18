package tools;

public class OpLogicos {
    private final Nodo nodo;

    public OpLogicos(Nodo nodo) {
        this.nodo = nodo;
    }

    public Object res(TablaSimbolos ts) {
        return res(nodo, ts);
    }

    public Object res(Nodo nn, TablaSimbolos ts) {
        if (nn.getHijos() == null) {
            if (nn.getValue().tipo == TipoToken.NUMBER || nn.getValue().tipo == TipoToken.STRING) {
                return nn.getValue().literal;
            } else if (nn.getValue().tipo == TipoToken.IDENTIFIER) {
                if (ts.existeIdentificador(nn.getValue().lexema)) {
                    return ts.obtener(nn.getValue().lexema);
                }
            }

        }
        Nodo izquierdo = nn.getHijos().get(0);
        Nodo derecho = nn.getHijos().get(1);

        Object resIzq = res(izquierdo, ts);
        Object resDer = res(derecho, ts);

        if (resIzq instanceof Double && resDer instanceof Double) {
            switch (nn.getValue().tipo) {
                case LESS:
                    return ((Double) resIzq < (Double) resDer);
                case LESS_EQUAL:
                    return ((Double) resIzq <= (Double) resDer);
                case GREATER:
                    return ((Double) resIzq > (Double) resDer);
                case GREATER_EQUAL:
                    return ((Double) resIzq >= (Double) resDer);
            }

        } else if (resIzq instanceof Boolean && resDer instanceof Boolean) {
            if (nn.getValue().tipo == TipoToken.AND) {
                return ((Boolean) resIzq && (Boolean) resDer);
            } else if (nn.getValue().tipo == TipoToken.OR) {
                return ((Boolean) resIzq || (Boolean) resDer);
            }
        } else {
            System.out.println("Error Tipos incompatibles");
            System.exit(0);
        }
        return null;
    }
}
