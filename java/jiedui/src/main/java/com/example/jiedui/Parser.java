package com.example.jiedui;

public class Parser {
    private int pos = -1;
    private int ch;
    private final String expression;

    public Parser(String expression) {
        this.expression = expression;
    }

    void nextChar() {
        ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
    }

    boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    public double parse() {
        nextChar();
        double x = parseExpression();
        if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
        return x;
    }

    double parseExpression() {
        double x = parseTerm();
        for (; ; ) {
            if (eat('+')) x += parseTerm(); // Addition
            else if (eat('-')) x -= parseTerm(); // Subtraction
            else return x;
        }
    }

    double parseTerm() {
        double x = parseFactor();
        for (; ; ) {
            if (eat('×')) x *= parseFactor(); // Multiplication
            else if (eat('÷')) x /= parseFactor(); // Division
            else return x;
        }
    }

    double parseFactor() {
        if (eat('+')) return parseFactor(); // Unary plus
        if (eat('-')) return -parseFactor(); // Unary minus

        double x;
        int startPos = this.pos;
        if (eat('(')) { // Parentheses
            x = parseExpression();
            eat(')');
        } else if ((ch >= '0' && ch <= '9') || ch == '/') { // Numbers
            while ((ch >= '0' && ch <= '9') || ch == '/') nextChar();
            x = Double.parseDouble(expression.substring(startPos, this.pos));
        } else {
            throw new RuntimeException("Unexpected: " + (char) ch);
        }

        return x;
    }
}
