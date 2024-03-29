package tools;

import parser.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Interprete {
    static boolean existenErrores = false;
    static TablaSimbolos ts = new TablaSimbolos();

    public static void main(String[] args) throws Exception {
        if (args.length > 1) {
            System.out.println("Uso correcto: interprete [archivo.txt]");
            System.exit(64);
        } else if (args.length == 1) {
            ejecutarArchivo(args[0]);
        } else {
            ejecutarPrompt();
        }
    }

    private static void ejecutarArchivo(String path) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        ejecutar(new String(bytes, Charset.defaultCharset()));
        if (existenErrores) System.exit(65);
    }

    private static void ejecutarPrompt() throws Exception {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String conjunto = "";

        while (true) {
            System.out.print(">>> ");
            String linea = reader.readLine();
            if (linea == null || linea.equals("salir"))
                break; // Ctrl + D
            ejecutar(linea);
            existenErrores = false;
        }
    }

    private static void ejecutar(String source) throws Exception {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scan();

        for(Token token : tokens){
        System.out.println(token);
        }

        try {
            Parser parser = new Parser(tokens);
            parser.parse();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    static void error(int linea, String mensaje) {
        reportar(linea, "", mensaje);
    }

    private static void reportar(int linea, String donde, String mensaje) {
        System.err.println(
                "[linea " + linea + "] Error " + donde + ": " + mensaje);
        existenErrores = true;
    }
}
