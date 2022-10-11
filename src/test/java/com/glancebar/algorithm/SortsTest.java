package com.glancebar.algorithm;

import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortsTest {

  private final Random random = new Random();

  private Integer[] elements;

  @BeforeEach
  void setUp() {
    elements = new Integer[1000];
    for (int i = 0; i < 1000; i++) {
      elements[i] = random.nextInt(1000);
    }
  }

  @Test
  void insertionSort() {
    Sorts.insertionSort(elements);
    assertAllElements();
  }

  @Test
  void chooseSort() {
    Sorts.chooseSort(elements);
    assertAllElements();
  }

  private void assertAllElements() {
    for (int i = 1; i < elements.length; i++) {
      Assertions.assertTrue(elements[i].compareTo(elements[i - 1]) >= 0);
    }
  }
}
