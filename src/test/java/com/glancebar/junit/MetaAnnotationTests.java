package com.glancebar.junit;

import org.junit.jupiter.api.Test;

public class MetaAnnotationTests {

  @Fast
  @Test
  void myFastTest() {
    // ...
  }

  @FastTest
  void myFastTest1() {
    // ...
  }
}
