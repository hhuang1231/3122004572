package com.example.jiedui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JieduiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JieduiApplication.class, args);

        if (args.length == 4 && args[0].equals("-n") && args[2].equals("-r")) {
            int numExercises = Integer.parseInt(args[1]);
            int rangeLimit = Integer.parseInt(args[3]);
            if (numExercises > 0 && rangeLimit > 0) {
                Generator.generate(numExercises, rangeLimit);
                System.out.println("Exercises and answers generated successfully!");
            } else {
                System.out.println("Invalid arguments. Both -n and -r values must be positive integers.");
            }
        } else if (args.length == 4 && args[0].equals("-e") && args[2].equals("-a")) {
            String exercisesFile = args[1];
            String answersFile = args[3];
            GradeCalculator.calculateGrade(exercisesFile, answersFile);
            System.out.println("Grading completed. Results written to Grade.txt");
        } else {
            System.out.println("Invalid arguments. Usage:\n" +
                    "To generate exercises:  -n <numExercises> -r <rangeLimit>\n" +
                    "To grade exercises:  -e <exercisesFile> -a <answersFile>");
        }
    }

}
