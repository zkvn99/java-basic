package com.minuk.chapter5.oneDimensionalArray.basic;

public class Application1 {

  public static void main(String[] args) {
    /* 길이가 10인 정수형 배열을 선언 및 할당한 뒤
     * 각 인덱스에 차례대로 1부터 10까지 값을 넣고 출력하세요
     * */

    int[] numbers = new int[10];

    int length = numbers.length;
    StringBuilder sb = new StringBuilder();

    for (int i = 1; i <= length; i++) {
      numbers[i - 1] = i;
      sb.append(numbers[i - 1]);
    }

    System.out.println(sb);
  }
}
