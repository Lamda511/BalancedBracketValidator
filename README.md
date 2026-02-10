# Balanced Bracket Validator

A robust, thread-safe Java utility to validate balanced brackets in a string.
Built with **Java 21** and **JUnit 5**.

## Features
- **Modern Stack Implementation:** Uses `java.util.ArrayDeque` for O(n) performance.
- **Fail-Fast:** Immediately rejects nulls and odd-length strings.
- **Extensible:** Bracket pairs are defined in a Map, allowing easy addition of new types (e.g., `<>`).

## Testing Strategy
The solution includes a comprehensive test suite built with **JUnit 5**:
- **Parameterized Tests:** validating edge cases, nulls, and boundary conditions.
- **Reflection-Based Testing:** ensures the utility class cannot be instantiated, achieving **100% code coverage** (Class, Method, and Line).

## Prerequisites
- Java 21 or higher
- Maven 3.8+

## How to Run

Run Interactive Mode

```bash
mvn compile exec:java -Dexec.mainClass="com.assessment.Main"
```

Or simply run the Main.java file in your IDE

### Run Unit Tests
```bash
mvn test
```

## Complexity Analysis

| Metric | Complexity | Explanation |
| :--- | :--- | :--- |
| **Time** | **O(n)** | The algorithm traverses the input string exactly once. |
| **Space** | **O(n)** | In the worst-case scenario (e.g., `((((...`), the stack grows linearly with the input size. |

### Why ArrayDeque?
I chose `java.util.ArrayDeque` over the legacy `java.util.Stack` class because `Stack` is synchronized (thread-safe), which introduces unnecessary overhead for this single-threaded validation task. `ArrayDeque` provides a more performant, modern implementation of the stack data structure.


## Constraints & Assumptions
The solution strictly adheres to the provided constraints:
1. **Sequence Length:** `1 < s < 1000`.
    - Sequences of length 0, 1, or >= 1000 return `false`.
2. **Character Set:** Input must strictly contain only `{`, `}`, `(`, `)`, `[`, `]`.
    - Any other characters (spaces, numbers, letters) immediately result in `false`.
