package com.glancebar.algorithm;

import com.glancebar.datatype.TreeNode;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;

public abstract class TreeTraversal {

  /**
   * Step 1 - Recursively traverse left subtree.<br>
   * Step 2 - Visit root node. <br>
   * Step 3 - Recursively traverse right subtree.
   */
  public static <T> List<T> inOrderTraversal(TreeNode<T> root) {
    List<T> elements = Lists.newArrayList();
    inOrderTraversal(root, elements);
    return elements;
  }

  /**
   * Step 1 - Visit root node. <br>
   * Step 2 - Recursively traverse left subtree. <br>
   * Step 3 - Recursively traverse right subtree.
   */
  public static <T> List<T> preOrderTraversal(TreeNode<T> root) {
    List<T> elements = Lists.newArrayList();
    preOrderTraversal(root, elements);
    return elements;
  }

  public static <T> List<T> postOrderTraversal(TreeNode<T> root) {
    List<T> elements = Lists.newArrayList();
    postOrderTraversal(root, elements);
    return elements;
  }

  private static <T> void inOrderTraversal(TreeNode<T> parent, List<T> elements) {
    if (Objects.isNull(parent)) {
      return;
    }
    inOrderTraversal(parent.getLeft(), elements);
    elements.add(parent.getVal());
    inOrderTraversal(parent.getRight(), elements);
  }

  private static <T> void preOrderTraversal(TreeNode<T> parent, List<T> elements) {
    if (Objects.isNull(parent)) {
      return;
    }
    elements.add(parent.getVal());
    preOrderTraversal(parent.getLeft(), elements);
    preOrderTraversal(parent.getRight(), elements);
  }

  private static <T> void postOrderTraversal(TreeNode<T> parent, List<T> elements) {
    if (Objects.isNull(parent)) {
      return;
    }
    postOrderTraversal(parent.getLeft(), elements);
    postOrderTraversal(parent.getRight(), elements);
    elements.add(parent.getVal());
  }
}
