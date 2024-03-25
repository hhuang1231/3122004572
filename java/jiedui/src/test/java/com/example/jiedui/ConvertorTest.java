package com.example.jiedui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ConvertorTest {

    @Test
    public void testEvaluateExpression() {
        String expression = "2 + 3 × (4 - 1)";
        int result = Convertor.evaluateExpression(expression);
        assertEquals(11, result);

        String expression2 = "6 ÷ 2";
        int result2 = Convertor.evaluateExpression(expression2);
        assertEquals(3, result2);
    }

    @Test
    public void testEvaluateInvalidExpression() {
        String invalidExpression = "2 + ";
        assertThrows(RuntimeException.class, () -> Convertor.evaluateExpression(invalidExpression));
    }
}
