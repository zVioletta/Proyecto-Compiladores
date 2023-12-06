public class ASDR {
    private int i = 0;
    private boolean err = false;
    private Token preAn;
    private final List<Token> tokens;

    public ASDR(List<Token> tokens) {
        this.tokens = tokens;
        preAn = tokens.get(i);
    }

    public String program() {

    }
}
