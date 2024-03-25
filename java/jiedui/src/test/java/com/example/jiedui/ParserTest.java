package com.example.jiedui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    @Test
    public void testParse() {
        String expression = "2 + 3 × (4 - 1)";
        Parser parser = new Parser(expression);
        double result = parser.parse();
        assertEquals(11.0, result, 0.001); // Verify the result is 11.0
    }

    @Test
    public void testParseInvalidExpression() {
        // Test parsing an invalid expression
        String invalidExpression = "2 + ";
        Parser parser = new Parser(invalidExpression);
        parser.parse();
    }
}
