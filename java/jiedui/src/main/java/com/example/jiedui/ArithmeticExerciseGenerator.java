package com.example.jiedui;

import java.io.*;
import java.util.*;

public class ArithmeticExerciseGenerator {
    private static final String[] OPERATORS = {"+", "-", "×", "÷"};

    private static String generateExpression(int rangeLimit) {
        Random random = new Random();
        StringBuilder expression = new StringBuilder();
        expression.append(random.nextInt(rangeLimit) + 1);

        int numOperators = random.nextInt(3) + 1; // Random number of operators (1 to 3)
        for (int i = 0; i < numOperators; i++) {
            String operator = OPERATORS[random.nextInt(OPERATORS.length)];
            expression.append(" ").append(operator).append(" ");
            if (operator.equals("÷")) {
                int denominator = random.nextInt(rangeLimit) + 1;
                int numerator = random.nextInt(denominator) + 1;
                expression.append(numerator).append("/").append(denominator);
            } else {
                expression.append(random.nextInt(rangeLimit) + 1);
            }
        }
        return expression.toString();
    }

    private static void writeToFile(String filename, List<String> lines) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateExercises(int numExercises, int rangeLimit) {
        List<String> exercises = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        for (int i = 0; i < numExercises; i++) {
            String expression = generateExpression(rangeLimit);
            exercises.add(expression);
            int answer = evaluateExpression(expression);
            answers.add(Integer.toString(answer));
        }

        writeToFile("Exercises.txt", exercises);
        writeToFile("Answers.txt", answers);
    }

    static int evaluateExpression(String expression) {
        return (int) Math.round(eval(expression));
    }

    private static double eval(String expression) {
        return new Object() {
            int pos = -1, ch;

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

            double parse() {
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
        }.parse();
    }
}