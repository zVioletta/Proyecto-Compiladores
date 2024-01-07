package parser;

import tools.TipoToken;
import tools.Token;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private int cPos = 0;
    private final List<Token> tokens;
    public boolean esValida = true;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void parse() {
        program();
    }

    // TODO Revisar GLC para poder realizar pruebas
    /*
     ! PROGRAM -> DECLARATION
     *
     * -----
     *
     ? DECLARATION -> FUN_DECLARATION DECLARATION
     ? DECLARATION -> VAR_DECLARATION DECLARATION
     ? DECLARATION -> STATEMENT DECLARATION
     ? DECLARATION -> Ɛ
     *
     ? FUN_DECLARATION -> fun FUNCTION
     ? VAR_DECLARATION -> var id VAR_INIT ;
     ? VAR_INIT -> = EXPRESSION
     ? VAR_INIT -> Ɛ
     *
     * -----
     *
     ! STATEMENT -> EXPR_STMT
     ! STATEMENT -> FOR_STMT
     ! STATEMENT -> IF_STMT
     ! STATEMENT -> PRINT_STMT
     ! STATEMENT -> RETURN_STMT
     ! STATEMENT -> WHILE_STMT
     ! STATEMENT -> BLOCK
     *
     ! EXPR_STMT -> EXPRESSION ;
     *
     ! FOR_STMT -> for ( FOR_STMT1 ; FOR_STMT2 ; FOR_STMT3 ) STATEMENT
     ! FOR_STMT1 -> VAR_DECLARATION
     ! FOR_STMT1 -> EXPR_STMT
     ! FOR_STMT1 -> ;
     ! FOR_STMT2 -> EXPRESSION ;
     ! FOR_STMT2 -> ;
     ! FOR_STMT3 -> EXPRESSION
     ! FOR_STMT3 -> Ɛ
     *
     ! IF_STMT -> if ( EXPRESSION ) STATEMENT ELSE_STATEMENT
     ! ELSE_STATEMENT -> else STATEMENT
     *
     ? PRINT_STMT -> print EXPRESSION ;
     *
     ? RETURN_STMT -> return RETURN_EXPR_OPC ;
     ? RETURN_EXPR_OPC -> EXPRESSION
     ? RETURN_EXPR_OPC -> Ɛ
     *
     ? WHILE_STMT -> while ( EXPRESSION ) STATEMENT
     *
     ! BLOCK -> { DECLARATION }
     *
     * -----
     *
     ! EXPRESSION -> ASSIGNMENT
     *
     ! ASSIGNMENT -> LOGIC_OR ASSIGNMENT_OPC
     ! ASSIGNMENT_OPC -> = EXPRESSION
     ! ASSIGNMENT_OPC -> Ɛ
     *
     ! LOGIC_OR -> LOGIC_AND LOGIC_OR_2
     ! LOGIC_OR_2 -> or LOGIC_AND LOGIC_OR_2
     ! LOGIC_OR_2 -> Ɛ
     *
     ! LOGIC_AND -> EQUALITY LOGIC_AND_2
     ! LOGIC_AND_2 -> and EQUALITY LOGIC_AND_2
     ! LOGIC_AND_2 -> Ɛ
     *
     ! EQUALITY -> COMPARISON EQUALITY_2
     ! EQUALITY_2 -> != COMPARISON EQUALITY_2
     ! EQUALITY_2 -> == COMPARISON EQUALITY_2
     ! EQUALITY_2 -> Ɛ
     *
     ! COMPARISON -> TERM COMPARISON_2
     ! COMPARISON_2 -> > TERM COMPARISON_2
     ! COMPARISON_2 -> < TERM COMPARISON_2
     ! COMPARISON_2 -> >= TERM COMPARISON_2
     ! COMPARISON_2 -> <= TERM COMPARISON_2
     ! COMPARISON_2 -> Ɛ
     *
     ! TERM -> FACTOR TERM_2
     ! TERM_2 -> + FACTOR TERM_2
     ! TERM_2 -> - FACTOR TERM_2
     ! TERM_2 -> Ɛ
     *
     ! FACTOR -> UNARY FACTOR_2
     ! FACTOR_2 -> * UNARY FACTOR_2
     ! FACTOR_2 -> / UNARY FACTOR_2
     ! FACTOR_2 -> Ɛ
     *
     ! UNARY -> ! UNARY
     ! UNARY -> - UNARY
     ! UNARY -> CALL
     *
     ! CALL -> PRIMARY CALL_2
     ! CALL_2 -> ( ARGUMENTS_OPC ) CALL_2
     ! CALL_2 -> Ɛ
     *
     ? PRIMARY -> ( EXPRESSION )
     ? PRIMARY -> NUMBER
     ? PRIMARY -> STRING
     ? PRIMARY -> TRUE
     ? PRIMARY -> FALSE
     ? PRIMARY -> IDENTIFIER
     ? PRIMARY -> NULL
     *
     * -----
     *
     ! FUNCTION -> id ( PARAMETERS_OPC ) BLOCK
     ! FUNCTIONS -> FUN_DECLARATION FUNCTIONS
     ! FUNCTIONS -> Ɛ
     *
     ! PARAMETERS_OPC -> PARAMETERS
     ! PARAMETERS_OPC -> Ɛ
     *
     ! PARAMETERS -> id PARAMETERS_2
     ! PARAMETERS_2 -> , id PARAMETERS_2
     ! PARAMETERS_2 -> Ɛ
     *
     ! ARGUMENTS_OPC -> EXPRESSION ARGUMENTS
     ! ARGUMENTS_OPC -> Ɛ
     *
     ! ARGUMENTS -> , EXPRESSION ARGUMENTS
     ! ARGUMENTS -> Ɛ
     */

    // ! PROGRAM
    private List<Statement> program() {
        List<Statement> program = new ArrayList<>();
        declaration(program);
        return program;
    }

    // ! DECLARATION
    private void declaration(List<Statement> program) {
        if (comparar(TipoToken.FUN)) {
            Statement function = funDeclaration();
            program.add(function);
            declaration(program);
        } else if (comparar(TipoToken.VAR)) {
            program.add(varDeclaration());
            declaration(program);
        } else {
            Statement stmt = statement();
            program.add(stmt);
            declaration(program);
        }
    }

    // ! FUN_DECLARATION
    private Statement funDeclaration() {
        match(TipoToken.FUN);
        return function();
    }

    // ! VAR_DECLARATION
    private Statement varDeclaration() {
        match(TipoToken.VAR);
        match(TipoToken.IDENTIFIER);
        Token id = previous();
        Expression expr = varInit();
        match(TipoToken.SEMICOLON);
        return new StmtVar(id, expr);
    }

    // ! VAR_INIT
    private Expression varInit() {
        if (comparar(TipoToken.EQUAL)) {
            match(TipoToken.EQUAL);
            Expression expr = expression();
            return expr;
        }
        return null;
    }

    // ! STATEMENT
    private Statement statement() {
        if (comparar(TipoToken.BANG) || comparar(TipoToken.MINUS) || comparar(TipoToken.TRUE) || comparar(TipoToken.FALSE) || comparar(TipoToken.NULL) || comparar(TipoToken.NUMBER) || comparar(TipoToken.STRING) || comparar(TipoToken.IDENTIFIER) || comparar(TipoToken.LEFT_PAREN)) {
            return exprStmt();
        } else if (comparar(TipoToken.FOR)) {
            return forStmt();
        } else if (comparar(TipoToken.IF)) {
            return ifStmt();
        } else if (comparar(TipoToken.WHILE)) {
            return whileStmt();
        } else if (comparar(TipoToken.RETURN)) {
            return returnStmt();
        } else if (comparar(TipoToken.LEFT_BRACE)) {
            return block();
        }
        return null;
    }

    // ! EXPR_STMT
    private Statement exprStmt() {
        Expression expr = expression();
        match(TipoToken.SEMICOLON);
        return new StmtExpression(expr);
    }

    // ! FOR_STMT
    private Statement forStmt() {
        match(TipoToken.FOR);
        match(TipoToken.LEFT_PAREN);
        forStmt1();
        match(TipoToken.SEMICOLON);
        forStmt2();
        match(TipoToken.SEMICOLON);
        forStmt3();
        match(TipoToken.RIGHT_PAREN);
        statement();
    }

    // ! FOR_STMT_1
    private void forStmt1() {
        if (comparar(TipoToken.VAR)) {
            varDeclaration();
        } else if (comparar(TipoToken.SEMICOLON)) {
            match(TipoToken.SEMICOLON);
        } else {
            exprStmt();
        }
    }

    // ! FOR_STMT_2
    private void forStmt2() {
        if (!comparar(TipoToken.SEMICOLON)) {
            expression();
        }
        match(TipoToken.SEMICOLON);
    }

    // ! FOR_STMT_3
    private void forStmt3() {
        if (!comparar(TipoToken.RIGHT_PAREN)) {
            expression();
        }
    }

    // ! IF_STMT
    private Object ifStmt() {
        match(TipoToken.IF);
        match(TipoToken.LEFT_PAREN);
        Expression conditional = expression();
        match(TipoToken.RIGHT_PAREN);
        Statement then = statement();
        Statement elseStmt = elseStmt();
    }

    // ! ELSE_STMT
    private Statement elseStmt() {
        if (comparar(TipoToken.ELSE)) {
            match(TipoToken.ELSE);
            return statement();
        }
        return null;
    }

    // ! PRINT_STMT
    private Statement printStmt() {
        match(TipoToken.PRINT);
        Expression expr = expression();
        match(TipoToken.SEMICOLON);
        return new StmtPrint(expr);
    }

    // ! RETURN_STMT
    private Statement returnStmt() {
        match(TipoToken.RETURN);
        Expression ret = returnStmtOpc();
        match(TipoToken.SEMICOLON);
        return new StmtReturn(ret);
    }

    // ! RETURN_STMT_OPC
    private Expression returnStmtOpc() {
        return expression();
    }

    // ! WHILE_STMT
    private Statement whileStmt() {
        match(TipoToken.WHILE);
        match(TipoToken.LEFT_PAREN);
        Expression expr = expression();
        match(TipoToken.RIGHT_PAREN);
        Statement stmt = statement();
        return new StmtLoop(expr, stmt);
    }

    // ! BLOCK
    private StmtBlock block(List<Statement> stmt) {
        match(TipoToken.LEFT_BRACE);
        stmt = new ArrayList<>();
        declaration(stmt);
        match(TipoToken.RIGHT_BRACE);
        return new StmtBlock(stmt);
    }

    // ! EXPRESSION
    private Expression expression() {
        return assignment();
    }

    // ! ASSIGNMENT
    private Expression assignment() {
        Expression or = logicOr();
        or = assignmentOpc(or);
        return or;
    }

    // ! ASSIGNMENTOPC
    private Expression assignmentOpc(Expression assign) {
        if (comparar(TipoToken.EQUAL)) {
            Token equal = ((ExprVariable) assign).name;
            match(TipoToken.EQUAL);
            Expression value = expression();
            return new ExprAssign(equal, value);
        }
        return assign;
    }

    // ! LOGIC_OR
    private Expression logicOr() {
        Expression and = logicAnd();
        and = logicOr2(and);
        return and;
    }

    // ! LOGIC_OR_2
    private Expression logicOr2( Expression izq) {
        Token operator;
        Expression der;
        Expression expb;
        if (comparar(TipoToken.OR)) {
            match(TipoToken.OR);
            operator = previous();
            der = logicAnd();
            expb = new ExprLogical(izq, operator, der);
            return logicOr2(expb);
        }
        return izq;
    }

    // ! LOGIC_AND
    private Expression logicAnd() {
        Expression eq = equality();
        eq = logicAnd2(eq);
        return eq;
    }

    // ! LOGIC_AND_2
    private Expression logicAnd2(Expression izq) {
        Token operator;
        Expression der;
        Expression expb;
        if (comparar(TipoToken.AND)) {
            match(TipoToken.AND);
            operator = previous();
            der = equality();
            expb = new ExprLogical(izq, operator, der);
            return logicAnd2(expb);
        }
        return izq;
    }

    // ! EQUALITY
    private Expression equality() {
        Expression comp = comparison();
        comp = equality2(comp);
        return comp;
    }

    // ! EQUALITY_2
    private Expression equality2(Expression izq) {
        Token operator;
        Expression der;
        Expression expb;
        if (comparar(TipoToken.BANG_EQUAL)) {
            match(TipoToken.BANG_EQUAL);
            operator = previous();
            der = comparison();
            expb = new ExprBinary(izq, operator, der);
            return equality2(expb);
        } else if (comparar(TipoToken.EQUAL_EQUAL)) {
            match(TipoToken.EQUAL_EQUAL);
            operator = previous();
            der = comparison();
            expb = new ExprBinary(izq, operator, der);
            return equality2(expb);
        }
        return izq;
    }

    // ! COMPARISON
    private Expression comparison() {
        Expression term = term();
        term = comparison2(term);
        return term;
    }

    // ! COMPARISON_2
    private Expression comparison2(Expression izq) {
        Token operator;
        Expression der;
        Expression expb;
        if (comparar(TipoToken.GREATER)) {
            match(TipoToken.GREATER);
            operator = previous();
            der = term();
            expb = new ExprBinary(izq, operator, der);
            return comparison2(expb);
        } else if (comparar(TipoToken.GREATER_EQUAL)) {
            match(TipoToken.GREATER_EQUAL);
            operator = previous();
            der = term();
            expb = new ExprBinary(izq, operator, der);
            return comparison2(expb);
        } else if (comparar(TipoToken.LESS)) {
            match(TipoToken.LESS);
            operator = previous();
            der = term();
            expb = new ExprBinary(izq, operator, der);
            return comparison2(expb);
        } else if (comparar(TipoToken.LESS_EQUAL)) {
            match(TipoToken.LESS_EQUAL);
            operator = previous();
            der = term();
            expb = new ExprBinary(izq, operator, der);
            return comparison2(expb);
        }
        return izq;
    }

    // ! TERM
    private Expression term() {
        Expression fact = factor();
        fact = term2(fact);
        return fact;
    }

    // ! TERM_2
    private Expression term2(Expression izq) {
        Token operator;
        Expression der;
        Expression expb;
        if (comparar(TipoToken.MINUS)) {
            match(TipoToken.MINUS);
            operator = previous();
            der = factor();
            expb = new ExprBinary(izq, operator, der);
            return term2(expb);
        } else if (comparar(TipoToken.PLUS)) {
            match(TipoToken.PLUS);
            operator = previous();
            der = factor();
            expb = new ExprBinary(izq, operator, der);
            return term2(expb);
        }
        return izq;
    }

    // ! FACTOR
    private Expression factor() {
        Expression expr = unary();
        expr = factor2(expr);
        return expr;
    }

    // ! FACTOR_2
    private Expression factor2(Expression izq) {
        Token operator;
        Expression der;
        ExprBinary expb;
        if (comparar(TipoToken.SLASH)) {
            match(TipoToken.SLASH);
            operator = previous();
            der = unary();
            expb = new ExprBinary(izq, operator, der);
            return factor2(expb);
        } else if (comparar(TipoToken.STAR)) {
            match(TipoToken.STAR);
            operator = previous();
            der = unary();
            expb = new ExprBinary(izq, operator, der);
            return factor2(expb);
        }
        return izq;
    }

    // ! UNARY
    private Expression unary() {
        Token operator;
        Expression expr;
        if (comparar(TipoToken.BANG)) {
            match(TipoToken.BANG);
            operator = previous();
            expr = unary();
            return new ExprUnary(operator, expr);
        } else if (comparar(TipoToken.MINUS)) {
            match(TipoToken.MINUS);
            operator = previous();
            expr = unary();
            return new ExprUnary(operator, expr);
        } else {
            return call();
        }
    }

    // ! CALL
    private Expression call() {
        Expression expr = primary();
        expr = call2(expr);
        return expr;
    }

    // ! CALL_2
    private Expression call2(Expression expr) {
        if (comparar(TipoToken.LEFT_PAREN)) {
            match(TipoToken.LEFT_PAREN);
            List<Expression> argumentList = argumentsOpc();
            match (TipoToken.RIGHT_PAREN);
            ExprCallFunction ecf = new ExprCallFunction(expr, argumentList);
            return call2(ecf);
        }
        return expr;
    }

    // ! PRIMARY
    private Expression primary() {
        if (comparar(TipoToken.IDENTIFIER)) {
            match(TipoToken.IDENTIFIER);
            Token id = previous();
            return new ExprVariable(id);
        } else if (comparar(TipoToken.NUMBER)) {
            match(TipoToken.NUMBER);
            Token num = previous();
            return new ExprLiteral(num);
        } else if (comparar(TipoToken.STRING)) {
            match(TipoToken.STRING);
            Token str = previous();
            return new ExprLiteral(str);
        } else if (comparar(TipoToken.TRUE)) {
            match(TipoToken.TRUE);
            return new ExprLiteral(true);
        } else if (comparar(TipoToken.FALSE)) {
            match(TipoToken.FALSE);
            return new ExprLiteral(false);
        } else if (comparar(TipoToken.NULL)) {
            match(TipoToken.NULL);
            return new ExprLiteral(null);
        } else if (comparar(TipoToken.LEFT_PAREN)) {
            match(TipoToken.LEFT_PAREN);
            Expression expr = expression();
            match(TipoToken.RIGHT_PAREN);
            return new ExprGrouping(expr);
        }
        return null;
    }

    // ! FUNCTION
    private Statement function() {
        match(TipoToken.IDENTIFIER);
        Token id = previous();
        match(TipoToken.LEFT_PAREN);
        List<Token> parameters = parametersOpc();
        match(TipoToken.RIGHT_PAREN);
        List<Statement> state = new ArrayList<>();
        StmtBlock block = block(state);
        return new StmtFunction(id, parameters, block);
    }

    // ! FUNCTIONS
    private void functions() {
        if (comparar(TipoToken.FUN)) {
            funDeclaration();
            functions();
        }
    }

    // ! PARAMETERSOPC
    private List<Token> parametersOpc() {
        List<Token> id = new ArrayList<>();
        if (comparar(TipoToken.IDENTIFIER)) {
            parameters(id);
        }
        return id;
    }

    // ! PARAMETERS
    private void parameters(List<Token> identifiers) {
        match(TipoToken.IDENTIFIER);
        Token id = previous();
        identifiers.add(id);
        parameters2(identifiers);
    }

    // ! PARAMETERS_2
    private void parameters2(List<Token> identifiers) {
        if (comparar(TipoToken.COMMA)) {
            match(TipoToken.COMMA);
            match(TipoToken.IDENTIFIER);
            Token id = previous();
            identifiers.add(id);
            parameters2(identifiers);
        }
    }

    // ! ARGUMENTSOPC
    private List<Expression> argumentsOpc() {
        List<Expression> argumentList = new ArrayList<>();
        Expression expr = expression();
        argumentList.add(expr);
        arguments(argumentList);
        return argumentList;
    }

    // ! ARGUMENTS
    private void arguments(List<Expression> expressions) {
        if (comparar(TipoToken.COMMA)) {
            match(TipoToken.COMMA);
            Expression expr = expression();
            expressions.add(expr);
            arguments(expressions);
        }
    }

    public void match(TipoToken tipoToken) {
        Token token = tokens.get(this.cPos);
        if (token.tipo == tipoToken) {
            cPos++;
        } else {
            esValida = false;
            System.out.println(
                    "Error: Se esperaba " + tipoToken + " pero se encontró " + token.tipo + " en la posición " + cPos);
        }
    }

    private boolean comparar(TipoToken tipoToken) {
        if (cPos >= tokens.size()) {
            return false;
        }
        Token token = tokens.get(cPos);
        return token.tipo == tipoToken;
    }

    public void error() {
        System.out.println("Error on: " + (this.cPos + 1) + ". Expecting:" + this.tokens.get(this.cPos));
    }

    private Token previous() {
        return this.tokens.get(cPos - 1);
    }
}