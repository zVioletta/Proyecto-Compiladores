package tools;

import java.util.List;

public class ASDR implements Parser{
    private int i = 0;
    private boolean err = false;
    private Token preAn;
    private final List<Token> tokens;

    public ASDR(List<Token> tokens){
        this.tokens = tokens;
        preAn = this.tokens.get(i);
    }

    @Override
    public boolean parse() {
        //TODO Llamado a la primera función
        return false;
    }



    /*  TENER EN CUENTA CÓDIGO PARA POSTERIORES REVISIONES
    //! Q -> select D from T
    private void Q(){
        match(tools.TipoToken.SELECT);
        D();
        match(tools.TipoToken.FROM);
        T();


    }

    //! D -> distinct P | P
    private void D(){
        if(err) return;

        //! Primera proyección D -> distinct P
        if(preAn.tipo == tools.TipoToken.DISTINCT){
            match(tools.TipoToken.DISTINCT);
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
            match(tools.TipoToken.ASTERISCO);
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
            match(tools.TipoToken.IDENTIFICADOR);
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
            match(tools.TipoToken.COMA);
            A();
        }
    }

    //! A3 -> .id | Ɛ
    private void A3(){
        if(err) return;

        if(preAn.tipo == tools.TipoToken.PUNTO){
            match(tools.TipoToken.PUNTO);
            match(tools.TipoToken.IDENTIFICADOR);
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
            match( tools.TipoToken.COMA );
            T();
        }
    }

    //! T2 -> idT3
    private void T2 (){

        if(err) return;

        if( this.preAn.tipo == tools.TipoToken.IDENTIFICADOR ) {
            match( tools.TipoToken.IDENTIFICADOR );
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
            match(tools.TipoToken.IDENTIFICADOR);
        }
    }
     */

    private void match(TipoToken tt){
        if(preAn.tipo == tt){
            i++;
            preAn = tokens.get(i);
        }else{
            err = true;
            System.out.println("Error encontrado");
        }
    }
}