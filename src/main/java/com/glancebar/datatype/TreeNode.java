package com.glancebar.datatype;

import lombok.Data;

/**
 * TreeNode implementation.
 *
 * @param <T>
 */
@Data
public class TreeNode<T> {
  private T val;
  private TreeNode<T> left;
  private TreeNode<T> right;

  public TreeNode() {}

  public TreeNode(T val) {
    this.val = val;
  }

  public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
