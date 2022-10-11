package com.glancebar.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

interface TestInterfaceDynamicTestsDemo {

  @TestFactory
  default Stream<DynamicTest> dynamicTestsForPalindromes() {
    return Stream.of("racecar", "radar", "mom", "dad")
        .map(text -> dynamicTest(text, () -> assertTrue(isPalindrome(text))));
  }

  boolean isPalindrome(String text);
}
