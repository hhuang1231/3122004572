package check;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class PaperChecker {


    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Please enter in the following format：java -jar main.jar [originalFilePath] [checkFilePath] [outputFilePath]");
            return;
        }

        String originalFilePath = args[0];
        String checkFilePath = args[1];
        String outputFilePath = args[2];


        System.out.println("Original File: " + originalFilePath);
        System.out.println("Plagiarized File: " + checkFilePath);
        System.out.println("Answer File: " + outputFilePath);

        String originalText = readFile(originalFilePath);
        String checkText = readFile(checkFilePath);

        int n = 3;

        double similarity = calculateSimilarity(originalText, checkText, n);
        System.out.println("similarity: " + similarity);
        writeResult(outputFilePath, similarity);
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private static void writeResult(String filePath, double similarity) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Similarity: " + similarity);
        }
    }

    public static double calculateSimilarity(String origin, String check, int cutLength) {
        if (cutLength < 0) {
            throw new IllegalArgumentException("Can not be plural");
        }
        HashSet<String> originSet = calculateHashes(origin, cutLength);
        HashSet<String> checkSet = calculateHashes(check, cutLength);

        HashSet<String> intersection = new HashSet<>(originSet);
        intersection.retainAll(checkSet);

        int unionSize = originSet.size() + checkSet.size() - intersection.size();
        return (double) intersection.size() / unionSize;
    }

    private static HashSet<String> calculateHashes(String text, int cutLength) {
        HashSet<String> hashes = new HashSet<>();
        for (int i = 0; i <= text.length() - cutLength; i++) {
            hashes.add(text.substring(i, i + cutLength));
        }
        return hashes;
    }

}
