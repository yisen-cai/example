package com.glancebar.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PseudoPalindromicTest {

    private final PseudoPalindromic pseudoPalindromic = new PseudoPalindromic();

    @Test
    void isPalindromic() {
        assertFalse(pseudoPalindromic.isPalindromic(Arrays.asList(2, 3, 1)));
    }

    @Test
    void pseudoPalindromicPaths() {
        TreeNode treeNode = new TreeNode(2);
        treeNode.right = new TreeNode(1);
        treeNode.right.right = new TreeNode(1);

        treeNode.left = new TreeNode(3);
        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(1);
        pseudoPalindromic.pseudoPalindromicPaths(treeNode);
    }
}