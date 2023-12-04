package tools;

import java.util.List;

public class ASDR implements Parser{
    public static int i = 0;
    public static Token preAn;
    private boolean err = false;
    public static List<Token> tokens = null;


    public ASDR(List<Token> tokens){
        ASDR.tokens = tokens;
        preAn = ASDR.tokens.get(i);
    }


    @Override
    public boolean parse() {
        return false;
    }

    /*
    // TENER EN CUENTA CÓDIGO PARA POSTERIORES REVISIONES
    //! Q -> select D from T
    private void Q(){
        Match(tools.TipoToken.SELECT);
        D();
        Match(tools.TipoToken.FROM);
        T();
    }

    //! D -> distinct P | P
    private void D(){
        if(err) return;

        //! Primera proyección D -> distinct P
        if(preAn.tipo == tools.TipoToken.DISTINCT){
            Match(tools.TipoToken.DISTINCT);
            P();
            //! Segunda proyección D -> P
        }else if (preAn.tipo == tools.TipoToken.ASTERISCO || preAn.tipo == tools.TipoToken.IDENTIFICADOR) {
            P();
        }else{
            err = true;
            System.out.println("Se esperaba 'distinct' o '*' o 'id'");

        }
    }

    //! P -> * | A
    private void P(){
        if(err) return;

        //! Primera proyección  P -> *
        if(preAn.tipo == tools.TipoToken.ASTERISCO){
            Match(tools.TipoToken.ASTERISCO);
            //! Segunda proyección  P -> A
        }else if(preAn.tipo == tools.TipoToken.IDENTIFICADOR){
            A();
        }
        else{
            err = true;
            System.out.println("Se esperaba '*' o 'id'");
        }
    }

    //! A -> A2 A1
    private void A(){
        if(err) return;

        A2();
        A1();
    }

    //! A2 -> id A3
    private void A2(){
        if(err) return;

        if(preAn.tipo == tools.TipoToken.IDENTIFICADOR){
            Match(tools.TipoToken.IDENTIFICADOR);
            A3();
        }else{
            err = true;
            System.out.println("Se esperaba 'id'");
        }
    }

    //! A1 -> ,A | Ɛ
    private void A1(){
        if(err) return;

        if(preAn.tipo == tools.TipoToken.COMA){
            Match(tools.TipoToken.COMA);
            A();
        }
    }

    //! A3 -> .id | Ɛ
    private void A3(){
        if(err) return;

        if(preAn.tipo == tools.TipoToken.PUNTO){
            Match(tools.TipoToken.PUNTO);
            Match(tools.TipoToken.IDENTIFICADOR);
        }
    }

    //! T -> T2T1
    private void T (){
        if(err) return;

        T2();
        T1();
    }

    //! T1 -> ,T | Ɛ
    private void T1 (){
        if(err) return;

        if ( this.preAn.tipo == tools.TipoToken.COMA ) {
            Match( tools.TipoToken.COMA );
            T();
        }
    }

    //! T2 -> idT3
    private void T2 (){

        if(err) return;

        if( this.preAn.tipo == tools.TipoToken.IDENTIFICADOR ) {
            Match( tools.TipoToken.IDENTIFICADOR );
            T3();
        }else{
            err = true;
            System.out.println("Se esperaba 'id'");
        }
    }

    //! T3 → id | Ɛ
    private void T3(){
        if(err) return;
        if(preAn.tipo == tools.TipoToken.IDENTIFICADOR){
            Match(tools.TipoToken.IDENTIFICADOR);
        }
    }
    */
}