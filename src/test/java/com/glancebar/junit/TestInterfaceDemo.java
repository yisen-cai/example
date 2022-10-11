package com.glancebar.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestInterfaceDemo
    implements TestLifecycleLogger, TimeExecutionLogger, TestInterfaceDynamicTestsDemo {

  @Test
  void isEqualValue() {
    assertEquals(1, "a".length(), "is always equal");
  }

  @Override
  public boolean isPalindrome(String text) {
    return true;
  }
}
