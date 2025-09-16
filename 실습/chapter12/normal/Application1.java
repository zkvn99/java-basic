package com.minuk.chapter12.normal;

import java.util.Arrays;

public class Application1 {

  public static void main(String[] args) {
    Integer[] intArray = {1, 2, 3, 4, 5};
    System.out.println("swap 전 : " + Arrays.toString(intArray));  // [1, 2, 3, 4, 5]
    swap(intArray, 1, 3);
    System.out.println("swap 후 : " + Arrays.toString(intArray));  // [1, 4, 3, 2, 5]

    String[] strArray = {"A", "B", "C", "D"};
    System.out.println("swap 전 : " + Arrays.toString(strArray));  // [A, B, C, D]
    swap(strArray, 0, 2);
    System.out.println("swap 후 : " + Arrays.toString(strArray));  // [C, B, A, D]
  }

  static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
