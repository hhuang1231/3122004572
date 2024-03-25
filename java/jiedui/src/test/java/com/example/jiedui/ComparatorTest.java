package com.example.jiedui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComparatorTest {

    @Test
    public void testEvaluateExpression() {
        String expression = "2 + 3 × (4 - 1)";
        int result = Comparator.evaluateExpression(expression);
        assertEquals(11, result);

        String expression2 = "6 ÷ 2";
        int result2 = Comparator.evaluateExpression(expression2);
        assertEquals(3, result2);
    }

    @Test
    public void testEvaluateInvalidExpression() {
        String invalidExpression = "2 + ";
        Comparator.evaluateExpression(invalidExpression);
    }
}
