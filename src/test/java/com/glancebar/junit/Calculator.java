package com.glancebar.junit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yishen.cai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calculator {
  public int add(int i, int i1) {
    return i + i1;
  }

  public int multiply(int i, int i1) {
    return i * i1;
  }

  public int divide(int i, int i1) {
    if (i1 == 0) {
      throw new ArithmeticException();
    }
    return i / i1;
  }

  public int subtract(int i, int i1) {
    return i - i1;
  }
}
