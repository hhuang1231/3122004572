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
        } else if ((ch >= '0' && ch <= '9') || ch == '/') { // Numbers or fraction
            StringBuilder numBuilder = new StringBuilder();
            while ((ch >= '0' && ch <= '9') || ch == '/' || ch == '÷') {
                if (ch == '÷') {
                    numBuilder.append('/');
                } else {
                    numBuilder.append((char) ch);
                }
                nextChar();
            }
            String numString = numBuilder.toString();
            // Check if it's a fraction
            if (numString.contains("/")) {
                String[] parts = numString.split("/");
                if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                    throw new RuntimeException("Invalid fraction: " + numString);
                }
                int numerator = Integer.parseInt(parts[0]);
                int denominator = Integer.parseInt(parts[1]);
                x = (double) numerator / denominator;
            } else {
                x = Double.parseDouble(numString);
            }
        } else {
            throw new RuntimeException("Unexpected: " + (char) ch);
        }

        return x;
    }
}
