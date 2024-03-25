package com.example.jiedui;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GradeCalculatorTest {

    @Test
    public void testCalculateGrade() {
        String exercisesFile = "Exercises.txt";
        String answersFile = "Answers.txt";

        // 调用 calculateGrade 方法计算评分
        GradeCalculator.calculateGrade(exercisesFile, answersFile);

        // 检查生成的评分文件是否存在且非空
        File gradeFile = new File("Grade.txt");
        assertTrue(gradeFile.exists());
        assertTrue(gradeFile.length() > 0);

    }
}
