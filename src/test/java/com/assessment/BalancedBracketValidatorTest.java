package com.assessment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BalancedBracketValidatorTest {

    @ParameterizedTest
    @DisplayName("Test that should return true for valid balanced strings within constraints")
    @ValueSource(strings = {
            "{[()]}",         // Nested
            "{{[[(())]]}}",   // Nested with duplicated brackets
            "{}[]()",         // Sequential
            "()",             // Length 2 (Min valid string of '(' and ')' )
            "[]",             // Length 2 (Min valid string of '[' and ']' )
            "{}",             // Length 2 (Min valid) string of '{' and '}' )
    })
    void testBalancedStrings(String input) {
        assertTrue(BalancedBracketValidator.balanced(input));
    }

    @ParameterizedTest
    @DisplayName("Test that should return false for non balanced strings")
    @ValueSource(strings = {
            "{[",            // Only open
            "]}",            // Only closed
            "{[(])}",        // Nested but not balanced
            "{[()",          // Open and not enough closed with even count
            "{{[()]}"        // Open and not enough closed with odd count
    })
    void testNonBalancedStrings(String input) {
        assertFalse(BalancedBracketValidator.balanced(input));
    }

    @ParameterizedTest
    @DisplayName("Test that should return false for invalid characters")
    @ValueSource(strings = {
            "(  )",   // Contains spaces
            "(ab)",   // Contains letters
            "1234",     // Numbers
            "()!?",     // Special chars
            "-+-/"      // Nonsense
    })
    void testInvalidCharacters(String input) {
        assertFalse(BalancedBracketValidator.balanced(input),
                "Should reject characters not in the set { ( ) { } [ ] }");
    }

    @ParameterizedTest
    @DisplayName("Test that should return false for null, empty and minimum length constraints (length <= 1)")
    @NullAndEmptySource
    @ValueSource(strings = {
            "(",            // Length 1 (Too short - open bracket)
            "}",            // Length 1 (Too short - closed bracket)
    })
    void testMinLengthConstraints(String input) {
        assertFalse(BalancedBracketValidator.balanced(input),
                "Should reject lengths <= 1");
    }

    @Test
    @DisplayName("Test that should return false for maximum length constraints (length >= 1000)")
    void testUpperBoundConstraint() {
        // Create a balanced string of length 1000
        String length1000 = "()".repeat(500);

        // Assert that the "balanced" method returns false.
        assertFalse(BalancedBracketValidator.balanced(length1000), "Should reject length 1000");
    }


    @Test
    @DisplayName("Constructor should throw exception (Reflection test)")
    void testPrivateConstructor() throws Exception {
        // Get the private constructor and make it accessible.
        Constructor<BalancedBracketValidator> constructor =
                BalancedBracketValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        // Assert that the calling throws InvocationTargetException
        InvocationTargetException exception =
                Assertions.assertThrows(
                        InvocationTargetException.class,
                        constructor::newInstance
                );

        // Verify the cause is an UnsupportedOperationException
        Assertions.assertInstanceOf(UnsupportedOperationException.class, exception.getCause());
    }
}