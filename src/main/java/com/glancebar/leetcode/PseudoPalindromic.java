package com.glancebar.leetcode;


import java.util.*;

/**
 * 1457. Pseudo-Palindromic Paths in a Binary Tree
 *
 * @see <a href="https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/">1457. Pseudo-Palindromic</a>
 */
public class PseudoPalindromic {


    public int pseudoPalindromicPaths(TreeNode root) {
        List<Integer> ints = new LinkedList<>();
        return recursionTreeNode(root, ints);
    }


    /**
     * Verify passed list is palindromic, time complexity is O^n
     */
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


    int recursionTreeNode(TreeNode node, List<Integer> ints) {
        if (Objects.isNull(node)) {
            return 0;
        }
        int res = 0;
        ints.add(node.val);
        if (isPalindromic(ints)) {
            res = 1;
        }
        if (Objects.isNull(node.left) && Objects.isNull(node.right)) {
            ints.remove(ints.size() - 1);
            return res;
        }
        return recursionTreeNode(node.left, ints) + recursionTreeNode(node.right, ints);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

