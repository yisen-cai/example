package com.glancebar.datatype;

/**
 * TreeNode implementation.
 *
 * @param <T>
 */
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

  public TreeNode<T> getLeft() {
    return left;
  }

  public void setLeft(TreeNode<T> left) {
    this.left = left;
  }

  public TreeNode<T> getRight() {
    return right;
  }

  public void setRight(TreeNode<T> right) {
    this.right = right;
  }

  public T getVal() {
    return val;
  }

  public void setVal(T val) {
    this.val = val;
  }
}
