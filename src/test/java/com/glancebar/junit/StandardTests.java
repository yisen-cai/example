package com.glancebar.junit;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.*;

class StandardTests {

  @BeforeAll
  static void initAll() {}

  @BeforeEach
  void init() {}

  @Test
  void succeedingTest() {}

  @Test
  @Disabled
  void failingTest() {
    fail("a failing test");
  }

  @Test
  @Disabled("for demonstration purposes")
  void skippedTest() {
    // not executed
  }

  @Test
  @Disabled
  void abortedTest() {
    assumeTrue("abc".contains("Z"));
    fail("test should have been aborted");
  }

  @AfterEach
  void tearDown() {}

  @AfterAll
  static void tearDownAll() {}
}
