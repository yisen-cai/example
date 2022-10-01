package com.glancebar.utils;

import com.glancebar.datatype.TreeNode;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

class InputDataUtilsTest {

  @Test
  void loadArrayToTreeNode() {
    var ints = Lists.newArrayList(4, 6, 2, 4, 8, 7, 6, 3, 1);
    TreeNode<Integer> intNode = InputDataUtils.loadArrayToTreeNode(ints);
  }
}
