package com.minuk.chapter5.twoDimensionalArray.hard;

import java.util.Random;
import java.util.Scanner;

public class Application1 {

  public static void main(String[] args) {
    /* 가로와 세로 길이를 정수형으로 입력 받아
     * 입력받은 가로 세로 길이를 이용하여 이차원 배열을 할당하고
     * 각 인덱스에는 랜덤으로 알파벳 대문자 넣어서 출력
     *
     * 단, 가로 행 혹은 세로 열은 반드시 1~10 까지의 정수를 입력해야 하고
     * 그렇지 않은 경우에는 "반드시 1~10까지의 정수를 입력해야 합니다. 다시 입력하세요." 출력 후
     * 가로 행 또는 세로 열을 다시 입력받을 수 있도록 한다.
     *
     * -- 입력 예시 --
     * 가로 행의 수를 입력하세요 : 5
     * 세로 열의 수를 입력하세요 : 4
     *
     * -- 출력 예시 --
     * F H Z G
     * W F O T
     * O R X V
     * W H J X
     * W S S J
     * */

    Scanner input = new Scanner(System.in);
    int rowLength;
    int colLength;
    while (true) {
      System.out.print("가로 행의 수를 입력하세요 : ");
      String row = input.nextLine();
      if (!row.matches("\\d+")) {
        System.out.println("반드시 1~10까지의 정수를 입력해야 합니다. 다시 입력하세요.");
        continue;
      }

      rowLength = Integer.parseInt(row);
      if (rowLength < 1 || rowLength > 10) {
        System.out.println("반드시 1~10까지의 정수를 입력해야 합니다. 다시 입력하세요.");
        continue;
      }

      System.out.print("세로 열의 수를 입력하세요 : ");
      String col = input.nextLine();
      if (!col.matches("\\d+")) {
        System.out.println("반드시 1~10까지의 정수를 입력해야 합니다. 다시 입력하세요.");
        continue;
      }

      colLength = Integer.parseInt(col);
      if (colLength < 1 || colLength > 10) {
        System.out.println("반드시 1~10까지의 정수를 입력해야 합니다. 다시 입력하세요.");
        continue;
      }

      break;
    }

    char[][] randomAlphabet = new char[rowLength][colLength];

    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < rowLength; i++) {
      for (int j = 0; j < colLength; j++) {
        randomAlphabet[i][j] = (char) (random.nextInt(26) + 65);
        sb.append(randomAlphabet[i][j]).append(' ');
      }
      sb.append('\n');
    }

    System.out.println(sb);
  }
}
