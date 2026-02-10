package com.assessment;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;

/**
 * Utility class to validate the balance of brackets in a string.
 */
public class BalancedBracketValidator {

    // Mapping of Closing -> Opening brackets
    private static final Map<Character, Character> BRACKET_PAIRS = Map.of(
            ')', '(',
            '}', '{',
            ']', '['
    );

    // Set of all allowed characters (openers + closers) for validation
    private static final Set<Character> ALLOWED_CHARS = Set.of(
            '(', ')', '[', ']', '{', '}'
    );

    // Prevent instantiation of the class as it is a utility class.
    private BalancedBracketValidator() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Checks if the sequence is balanced according to specific constraints.
     * * Constraints:
     * 1. 1 < length < 1000
     * 2. Contains only characters: (, ), [, ], {, }
     *
     * @param s The string to validate
     * @return true if balanced and meets all constraints, false otherwise
     */
    public static boolean balanced(String s) {

        // Handle Null and Length Constraints (1 < s < 1000)
        if (s == null || s.length() <= 1 || s.length() >= 1000) {
            return false;
        }

        // Check for odd lengths, as they can never be balanced
        if (s.length() % 2 != 0) {
            return false;
        }

        // Using ArrayDeque as a Stack (faster than java.util.Stack)
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            // Allowed characters validation
            if (!ALLOWED_CHARS.contains(c)) {
                return false; // Invalid character found
            }

            if (BRACKET_PAIRS.containsValue(c)) {
                // It's an open bracket
                stack.push(c);
            } else {
                // It's a closing bracket
                // If stack is empty or top doesn't match -> Invalid
                if (stack.isEmpty() || stack.pop() != BRACKET_PAIRS.get(c)) {
                    return false;
                }
            }
        }

        // Valid only if the stack is completely empty at the end
        return stack.isEmpty();
    }
}