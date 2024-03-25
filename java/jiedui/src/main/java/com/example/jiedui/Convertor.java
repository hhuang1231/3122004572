package com.example.jiedui;

public class Convertor {
    public static int evaluateExpression(String expression) {
        return (int) Math.round(eval(expression));
    }


    private static double eval(String expression) {
        return new Parser(expression).parse();
    }
}
