package com.glancebar.leetcode;

import static org.junit.jupiter.api.Assertions.*;

import com.glancebar.datatype.TreeNode;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class PseudoPalindromicTest {

  private final PseudoPalindromic pseudoPalindromic = new PseudoPalindromic();

  @Test
  void isPalindromic() {
    assertFalse(pseudoPalindromic.isPalindromic(Arrays.asList(2, 3, 1)));
  }

  @Test
  void pseudoPalindromicPaths() {
    TreeNode<Integer> treeNode = new TreeNode<>(2);
    treeNode.setRight(new TreeNode<>(1));
    treeNode.getRight().setRight(new TreeNode<>(1));

    treeNode.setLeft(new TreeNode<>(3));
    treeNode.getLeft().setLeft(new TreeNode<>(3));
    treeNode.getLeft().setRight(new TreeNode<>(1));
    pseudoPalindromic.pseudoPalindromicPaths(treeNode);
  }
}
