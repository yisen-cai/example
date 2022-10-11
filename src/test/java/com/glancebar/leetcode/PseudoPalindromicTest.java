package com.glancebar.leetcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class PseudoPalindromicTest {

  private final PseudoPalindromic pseudoPalindromic = new PseudoPalindromic();

  @Test
  void isPalindromic() {
    System.out.println("hello world");
    assertFalse(pseudoPalindromic.isPalindromic(Arrays.asList(2, 3, 1)));
  }

  @Test
  void pseudoPalindromicPaths() {}
}
