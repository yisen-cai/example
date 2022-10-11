package com.glancebar.algorithm;

public abstract class Sorts {

  @SafeVarargs
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void mergeSort(
      int leftStart, int rightStart, int length, T... elements) {
    int leftLen = rightStart - leftStart;
    int rightLen = length - rightStart;
    T[] leftEle = (T[]) new Object[leftLen];
    for (int i = leftStart; i < leftStart + leftLen; i++) {
      leftEle[i - leftStart] = elements[leftStart];
    }
    T[] rightEle = (T[]) new Object[rightLen];
    for (int i = rightStart; i < rightStart + rightLen; i++) {
      rightEle[i - rightStart] = elements[rightStart];
    }
  }

  @SafeVarargs
  public static <T extends Comparable<T>> void chooseSort(T... elements) {
    for (int i = 0; i < elements.length; i++) {
      T lowest = elements[i];
      int j = i + 1;
      for (; j < elements.length; j++) {
        if (lowest.compareTo(elements[j]) > 0) {
          T temp = lowest;
          lowest = elements[j];
          elements[j] = temp;
        }
      }
      elements[i] = lowest;
    }
  }

  @SafeVarargs
  public static <T extends Comparable<T>> void insertionSort(T... elements) {
    for (int i = 1; i < elements.length; i++) {
      T key = elements[i];
      int j = i - 1;
      // keep unchanged
      while (j >= 0 && elements[j].compareTo(key) > 0) {
        elements[j + 1] = elements[j];
        j--;
      }
      // last stored element.
      elements[j + 1] = key;
    }
  }
}
