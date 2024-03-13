package check;


public class PaperCheckerTest {
    public static void main(String[] args) {
        if (testSameText()) {
            System.out.println("testSameText success");
        } else {
            System.out.println("testSameText  fail");
        }
        if (testCompletelyDifferentText()) {
            System.out.println("testCompletelyDifferentText success");
        } else {
            System.out.println("testCompletelyDifferentText  fail");
        }
        if (testPartiallySimilarText()) {
            System.out.println("testPartiallySimilarText success");
        } else {
            System.out.println("testPartiallySimilarText  fail");
        }
        if (testEmptyText()) {
            System.out.println("testEmptyText success");
        } else {
            System.out.println("testEmptyText  fail");
        }
        if (testLongerTextFirst()) {
            System.out.println("testLongerTextFirst success");
        } else {
            System.out.println("testLongerTextFirst  fail");
        }
        if (testLongerTextSecond()) {
            System.out.println("testLongerTextSecond success");
        } else {
            System.out.println("testLongerTextSecond  fail");
        }
        if (testSpecialCharacters()) {
            System.out.println("testSpecialCharacters success");
        } else {
            System.out.println("testSpecialCharacters  fail");
        }
        if (testNegativeCutLength()) {
            System.out.println("testNegativeCutLength success");
        } else {
            System.out.println("testNegativeCutLength  fail");
        }
        if (testZeroCutLength()) {
            System.out.println("testZeroCutLength success");
        } else {
            System.out.println("testZeroCutLength  fail");
        }
        if (testLargeCutLength()) {
            System.out.println("testLargeCutLength success");
        } else {
            System.out.println("testLargeCutLength  fail");
        }
    }

    static boolean testSameText() {
        String text = "This is a test text.";
        double similarity = PaperChecker.calculateSimilarity(text, text, 3);
        return 1.0 == similarity;
    }


    static boolean testCompletelyDifferentText() {
        String text1 = "This is a test text.";
        String text2 = "This is another test text.";
        double similarity = PaperChecker.calculateSimilarity(text1, text2, 3);
        return 0.0 < similarity;
    }


    static boolean testPartiallySimilarText() {
        String text1 = "This is a test text.";
        String text2 = "This is another test.";
        double similarity = PaperChecker.calculateSimilarity(text1, text2, 3);
        return 0.0 < similarity;
    }


    static boolean testEmptyText() {
        double similarity = PaperChecker.calculateSimilarity("", "", 3);
        return 0.0 != similarity;
    }


    static boolean testLongerTextFirst() {
        String text1 = "This is a longer test text for comparison.";
        String text2 = "This is a test text.";
        double similarity = PaperChecker.calculateSimilarity(text1, text2, 3);
        return 0.0 != similarity;
    }


    static boolean testLongerTextSecond() {
        String text1 = "This is a test text.";
        String text2 = "This is a longer test text for comparison.";
        double similarity = PaperChecker.calculateSimilarity(text1, text2, 3);
        return 0.0 != similarity;
    }


    static boolean testSpecialCharacters() {
        String text1 = "This is a test text with special characters: !@#$%^&*()";
        String text2 = "This is another test text with special characters: !@#$%^&*()";
        double similarity = PaperChecker.calculateSimilarity(text1, text2, 3);
        return 1.0 != similarity;
    }


    static boolean testNegativeCutLength() {
        String text1 = "This is a test text.";
        String text2 = "This is another test text.";
        try {
            double similarity = PaperChecker.calculateSimilarity(text1, text2, -1);
        } catch (IllegalArgumentException e) {
            return true;
        }
        return true;
    }


    static boolean testZeroCutLength() {
        String text1 = "This is a test text.";
        String text2 = "This is another test text.";
        double similarity = PaperChecker.calculateSimilarity(text1, text2, 0);
        return 0.0 < similarity;
    }


    static boolean testLargeCutLength() {
        String text1 = "This is a test text.";
        String text2 = "This is another test text.";
        double similarity = PaperChecker.calculateSimilarity(text1, text2, 100);
        return 0.0 != similarity;
    }
}
