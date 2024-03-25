package com.example.jiedui;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GradeCalculator {

    public static void calculateGrade(String exercisesFile, String answersFile) {
        try {
            List<String> exercises = readLines(exercisesFile);
            List<String> answers = readLines(answersFile);
            List<Integer> correctIndices = new ArrayList<>();
            List<Integer> wrongIndices = new ArrayList<>();

            for (int i = 0; i < exercises.size(); i++) {
                int expectedAnswer = Integer.parseInt(answers.get(i));
                int actualAnswer = Convertor.evaluateExpression(exercises.get(i));
                if (actualAnswer == expectedAnswer) {
                    correctIndices.add(i + 1);
                } else {
                    wrongIndices.add(i + 1);
                }
            }

            writeToFile(correctIndices, wrongIndices);
        } catch (IOException e) {
            log.error("结果输出至文件异常", e);
        }
    }

    /**
     * 读取配置文件
     */
    private static List<String> readLines(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static void writeToFile(List<Integer> correctIndices, List<Integer> wrongIndices) {
        try (PrintWriter writer = new PrintWriter("Grade.txt")) {
            writer.println("Correct: " + correctIndices.size() + " (" + formatIndices(correctIndices) + ")");
            writer.println("Wrong: " + wrongIndices.size() + " (" + formatIndices(wrongIndices) + ")");
        } catch (FileNotFoundException e) {
            log.error("结果输出至文件异常", e);
        }
    }

    /**
     * 格式化输出
     */
    private static String formatIndices(List<Integer> indices) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indices.size(); i++) {
            builder.append(indices.get(i));
            if (i < indices.size() - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
