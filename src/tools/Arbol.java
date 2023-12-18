package tools;

public class Arbol {
    private final Nodo raiz;

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }

    public void recorrer(TablaSimbolos ts) {

        for (Nodo n : raiz.getHijos()) {
            Token t = n.getValue();
            switch (t.tipo) {
                // Operadores aritméticos

                case VAR:
                    if (n.getHijos().size() > 1) {
                        Nodo izquierdo = n.getHijos().get(0);

                        Nodo derecho = n.getHijos().get(1);
                        switch (derecho.getValue().tipo) {
                            case PLUS:
                            case MINUS:
                            case STAR:
                            case SLASH:
                                SolverAritmetico solver = new SolverAritmetico(derecho);
                                Object resultado = solver.resolver(ts);
                                if (ts.existeIdentificador(izquierdo.getValue().lexema)) {
                                    Interprete.error(izquierdo.getValue().posicion, "Variable Existente");
                                } else {
                                    ts.asignar(izquierdo.getValue().lexema, resultado);

                                }
                                break;
                            default:
                                if (ts.existeIdentificador(izquierdo.getValue().lexema)) {
                                    Interprete.error(izquierdo.getValue().posicion, "Variable Existente");
                                } else {
                                    ts.asignar(izquierdo.getValue().lexema, derecho.getValue().literal);
                                }
                                break;
                        }
                    } else {
                        Nodo izquierdo = n.getHijos().get(0);
                        if (ts.existeIdentificador(izquierdo.getValue().lexema)) {
                            Interprete.error(izquierdo.getValue().posicion, "Variable Existente");
                        } else {
                            ts.asignar(izquierdo.getValue().lexema, null);
                        }
                    }

                    break;
                case PRINT:
                    Nodo izquierdo01 = n.getHijos().get(0);
                    switch (izquierdo01.getValue().tipo) {
                        case PLUS:
                            // System.out.println("Ayudaporfavor");
                            SolverAritmetico solver = new SolverAritmetico(izquierdo01);
                            Object resultado = solver.resolver(ts);
                            System.out.println(resultado);
                        default:
                            if (ts.existeIdentificador(izquierdo01.getValue().lexema)) {

                                System.out.println(ts.obtener(izquierdo01.getValue().lexema));
                            } else {
                                if (izquierdo01.getValue().literal != null) {
                                    System.out.println(izquierdo01.getValue().literal);
                                }

                            }
                            break;
                    }
                    break;

                case IF:
                    Nodo izquierdo02 = n.getHijos().get(0);
                    OpLogicos logic = new OpLogicos(izquierdo02);
                    Object res01 = logic.res(ts);
                    if ((Boolean) res01) {
                        if (n.getHijos().size() > 2) {
                            for (int i = 0; i < n.getHijos().size(); i++) {
                                Nodo der01 = n.getHijos().get(i);
                                switch (der01.getValue().tipo) {
                                    case PRINT:
                                        Nodo izquierda3 = der01.getHijos().get(0);
                                        if (ts.existeIdentificador(izquierda3.getValue().lexema)) {
                                            System.out.println(ts.obtener(izquierda3.getValue().lexema));
                                        } else {
                                            if (izquierda3.getValue().literal != null) {
                                                System.out.println(izquierda3.getValue().literal);
                                            }
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else {
                            Nodo der01 = n.getHijos().get(1);
                            switch (der01.getValue().tipo) {
                                case PRINT:
                                    Nodo izquierda3 = der01.getHijos().get(0);
                                    if (ts.existeIdentificador(izquierda3.getValue().lexema)) {
                                        System.out.println(ts.obtener(izquierda3.getValue().lexema));
                                    } else {
                                        if (izquierda3.getValue().literal != null) {
                                            System.out.println(izquierda3.getValue().literal);
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        if (n.getHijos().size() > 2) {
                            Nodo aux = n.getHijos().get(2);
                            if (aux.getHijos().size() > 2) {
                                for (int i = 0; i < aux.getHijos().size(); i++) {
                                    Nodo derecha01 = aux.getHijos().get(i);
                                    switch (derecha01.getValue().tipo) {
                                        case PRINT:
                                            Nodo izquierda03 = derecha01.getHijos().get(0);
                                            if (ts.existeIdentificador(izquierda03.getValue().lexema)) {
                                                System.out.print(ts.obtener(izquierda03.getValue().lexema));
                                            } else {
                                                if (izquierda03.getValue().literal != null) {
                                                    System.out.println(izquierda03.getValue().literal);
                                                }
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            } else {
                                Nodo derecha01 = aux.getHijos().get(0);
                                switch (derecha01.getValue().tipo) {
                                    case PRINT:
                                        Nodo izquierda03 = derecha01.getHijos().get(0);
                                        if (ts.existeIdentificador(izquierda03.getValue().lexema)) {
                                            System.out.print(ts.obtener(izquierda03.getValue().lexema));
                                        } else {
                                            if (izquierda03.getValue().literal != null) {
                                                System.out.println(izquierda03.getValue().literal);
                                            }
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                    break;
                case WHILE:
                    System.out.println("TBI");
                    break;
                case FOR:
                    System.out.println("TBI");
                    break;
                default:
                    break;

            }
        }
    }
}
