package com.assessment;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Running Predefined Checks ---");
        runCheck("{[()]}"); // Should return true
        runCheck("{[(])}"); // Should return false
        runCheck("{{[[(())]]}}"); // Should return true

        // Interactive Mode
        System.out.println("\n--- Interactive Mode ---");
        System.out.println("Type a string to check (or type 'exit' to quit):");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input.trim())) {
                    System.out.println("Exiting...");
                    break;
                }

                runCheck(input);
            }
        }
    }

    private static void runCheck(String input) {
        boolean result = BalancedBracketValidator.balanced(input);
        System.out.printf("Input: \"%-10s\" | Balanced: %s%n", input, result ? "YES" : "NO");
    }
}