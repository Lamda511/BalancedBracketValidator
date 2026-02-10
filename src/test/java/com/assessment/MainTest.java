package com.assessment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class MainTest {

    private final InputStream originalIn = System.in;

    @AfterEach
    void tearDown() {
        // Restore the original System.in after the test
        System.setIn(originalIn);
    }

    @Test
    @DisplayName("Test for the main method that no unexpected error is thrown")
    void testMainMethodExecution() {
        // Simulate user typing: "()" then ENTER, then "exit" then ENTER
        // and redirect System.in to use the simulated input
        String simulatedInput = "()\nexit\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Run the main method
        Assertions.assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}