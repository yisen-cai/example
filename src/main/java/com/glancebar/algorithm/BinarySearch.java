package com.glancebar.algorithm;

import com.glancebar.datatype.TreeNode;

public abstract class BinarySearch {

  @SafeVarargs
  public static <T extends Comparable<T>> int search(
      T target, int leftInd, int rightInd, T... sortedElements) {
    if (leftInd > rightInd) {
      return -1;
    }
    int len = rightInd - leftInd + 1;
    if (len == 1) {
      if (sortedElements[rightInd].compareTo(target) == 0) {
        return rightInd;
      }
      return -1;
    }
    int midInd = (rightInd + leftInd) / 2;
    T mid = sortedElements[midInd];
    if (mid.compareTo(target) > 0) {
      return search(target, midInd + 1, rightInd, sortedElements);
    }
    if (mid.compareTo(target) == 0) {
      return midInd;
    } else {
      return search(target, leftInd, midInd - 1, sortedElements);
    }
  }

  public static <T> T search(TreeNode<T> rootNode) {
    return null;
  }
}
