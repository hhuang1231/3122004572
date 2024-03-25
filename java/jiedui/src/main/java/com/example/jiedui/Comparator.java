package com.example.jiedui;

public class Comparator {
    public static int evaluateExpression(String expression) {
        return (int) Math.round(eval(expression));
    }


    private static double eval(String expression) {
        return new Parser(expression).parse();
    }
}
