package com.glancebar.datatype;

import lombok.Data;

@Data
public class LinkedNode<T> {
  private T value;

  private LinkedNode<T> next;
}
