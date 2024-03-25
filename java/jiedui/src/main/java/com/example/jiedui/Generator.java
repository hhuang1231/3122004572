package com.example.jiedui;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

@Slf4j
public class Generator {
    private static final String[] OPERATORS = {"+", "-", "×", "÷"};

    public static void generate(int numExercises, int rangeLimit) {
        List<String> exercises = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        for (int i = 0; i < numExercises; i++) {
            String expression = generateExpression(rangeLimit);
            exercises.add(expression);
            int answer = Comparator.evaluateExpression(expression);
            answers.add(Integer.toString(answer));
        }

        writeToFile("Exercises.txt", exercises);
        writeToFile("Answers.txt", answers);
    }

    private static String generateExpression(int rangeLimit) {
        Random random = new Random();
        StringBuilder expression = new StringBuilder();
        expression.append(random.nextInt(rangeLimit) + 1);

        // Random number of operators (1 to 3)
        int numOperators = random.nextInt(3) + 1;
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
            log.error("结果输出至文件异常", e);
        }
    }

    private static boolean isValidExpression(String expression) {
        // Check if expression follows rules 3, 4, and 6
        return !expression.contains("-") && !expression.contains("÷") && countOperators(expression) <= 3;
    }

    private static int countOperators(String expression) {
        int count = 0;
        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '×' || c == '÷') {
                count++;
            }
        }
        return count;
    }


}