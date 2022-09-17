package com.glancebar.leetcode;

import com.glancebar.datatype.TreeNode;
import java.util.*;

/**
 * 1457. Pseudo-Palindromic Paths in a Binary Tree
 *
 * @see <a href="https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/">1457.
 *     Pseudo-Palindromic</a>
 */
public class PseudoPalindromic {

  public int pseudoPalindromicPaths(TreeNode<Integer> root) {
    List<Integer> ints = new LinkedList<>();
    return recursionTreeNode(root, ints);
  }

  /** Verify passed list is palindromic, time complexity is O^n */
  public boolean isPalindromic(List<Integer> ints) {
    System.out.println(ints);
    Set<Integer> sets = new HashSet<>();
    for (int curr : ints) {
      if (sets.contains(curr)) {
        sets.remove(curr);
      } else {
        sets.add(curr);
      }
    }
    return ints.size() == 0 || ints.size() == 1;
  }

  int recursionTreeNode(TreeNode<Integer> node, List<Integer> ints) {
    if (Objects.isNull(node)) {
      return 0;
    }
    int res = 0;
    ints.add(node.getVal());
    if (isPalindromic(ints)) {
      res = 1;
    }
    if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
      ints.remove(ints.size() - 1);
      return res;
    }
    return recursionTreeNode(node.getLeft(), ints) + recursionTreeNode(node.getRight(), ints);
  }
}
