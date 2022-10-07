package com.glancebar.algorithm;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class SortsTest {

  @Test
  void insertionSort() {
    Integer[] elements = {3, 2, 6, 8, 1, 0, 4, -1};
    Sorts.insertionSort(elements);
    Arrays.stream(elements).forEach(System.out::println);
  }

  @Test
  void choosingSort() {
    Integer[] elements = {3, 2, 6, 8, 1, 0, 4, -1};
    Sorts.chooseSort(elements);
    Arrays.stream(elements).forEach(System.out::println);
  }
}
