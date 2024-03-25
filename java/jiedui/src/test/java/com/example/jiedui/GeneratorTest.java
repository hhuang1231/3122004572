package com.example.jiedui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

public class GeneratorTest {

    @Test
    public void testGenerate() {
        int numExercises = 1000;
        int rangeLimit = 10;

        // 调用 generate 方法生成题目和答案
        Generator.generate(numExercises, rangeLimit);

        // 检查生成的题目文件是否存在且非空
        File exercisesFile = new File("Exercises.txt");
        assertTrue(exercisesFile.exists());
        assertTrue(exercisesFile.length() > 0);

        // 检查生成的答案文件是否存在且非空
        File answersFile = new File("Answers.txt");
        assertTrue(answersFile.exists());
        assertTrue(answersFile.length() > 0);

    }
}
