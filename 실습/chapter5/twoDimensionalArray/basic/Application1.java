package com.minuk.chapter5.twoDimensionalArray.basic;

public class Application1 {

  public static void main(String[] args) {

    /* 행의 길이가 3, 열의 길이가 4인 정수형 2차원 배열을 선언 및 할당하고,
     * 각 인덱스에 차례대로 1부터 12까지의 정수를 대입한 뒤
     * 그 값을 출력하는 코드를 작성하시오.
     *
     * -- 출력 예시 --
     * 1  2  3  4
     * 5  6  7  8
     * 9 10 11 12
     *
     * 단, 출력물의 줄맞춤은 신경쓰지 말고 풀어보세요
     * */

    int[][] numbers = new int[3][4];
    StringBuilder sb = new StringBuilder();

    int seq = 0;
    for (int i = 0; i < numbers.length; i++) {
      for (int j = 0; j < numbers[i].length; j++) {
        numbers[i][j] = ++seq;
        sb.append(numbers[i][j]).append(" ");
      }
      sb.append('\n');
    }

    System.out.println(sb);
  }
}
