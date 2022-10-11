package com.glancebar.datatype;

import lombok.Data;

@Data
public class DoubleLinkedNode<T> {

  private T value;

  private DoubleLinkedNode<T> next;

  private DoubleLinkedNode<T> prev;
}
