package com.craftinterpreters.lox;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Lox {
    //> Evaluating Expressions interpreter-instance
    //private static final Interpreter interpreter = new Interpreter();
    //< Evaluating Expressions interpreter-instance
//> had-error
    static boolean hadError = false;
    //< had-error
//> Evaluating Expressions had-runtime-error-field
    static boolean hadRuntimeError = false;

//< Evaluating Expressions had-runtime-error-field
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if ( args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }
    //> run-file
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        //< exit-code
        // Indicate an error in the exit code.
        if (hadError) System.exit(65);
//< exit-code
// > Evaluating Expressions check-runtime-error
        if (hadRuntimeError) System.exit(70);
    //< Evaluating Expressions check-runtime-error
    }
    //< run-file
    //> prompt
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) { // [repl]
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
//> reset-had-error
            hadError = false;
//< reset-had-error
        }
    }
    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        //List<Token> tokens = scanner.scanTokens();
// Scanning run < Parsing Expressions print-ast
    // For now, just print the tokens.
//    for (Token token : tokens) {
//      System.out.println(token);
//    }
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where,
                               String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}
