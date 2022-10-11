package com.glancebar.utils;

import com.glancebar.datatype.TreeNode;
import java.util.List;
import java.util.Objects;

/** Common input data utils. */
public abstract class InputDataUtils {

  /** Loading list of input and transforming it to TreeNode. */
  public static <T> TreeNode<T> loadArrayToTreeNode(List<T> elements) {
    int size = Objects.requireNonNull(elements).size();
    if (size == 0) {
      return null;
    }
    TreeNode<T> root = new TreeNode<>();
    root.setVal(elements.get(0));
    loadArrayToTreeNode(0, root, elements);
    return root;
  }

  private static <T> void loadArrayToTreeNode(int index, TreeNode<T> parent, List<T> elements) {
    int currInd = index * 2;
    if (currInd + 1 < elements.size()) {
      TreeNode<T> left = new TreeNode<>();
      left.setVal(elements.get(currInd + 1));
      parent.setLeft(left);
      loadArrayToTreeNode(currInd + 1, left, elements);
    }
    if (currInd + 2 < elements.size()) {
      TreeNode<T> right = new TreeNode<>();
      right.setVal(elements.get(currInd + 2));
      parent.setRight(right);
      loadArrayToTreeNode(currInd + 2, right, elements);
    }
  }
}
