package com.minuk.chapter9.basic;

import java.util.Scanner;

public class Application1 {

  public static void main(String[] args) {
    /* ----- 입력 예시 -----
     *
     * 문자열 입력 : I will be a good developer.
     *
     * ----- 출력 예시 -----
     *
     * 변환된 문자열: I Will Be A Good Developer.
     * 총 단어 개수: 6
     */
    Scanner input = new Scanner(System.in);
    System.out.print("문자열 입력 : ");
    String line = input.nextLine();
    String[] words = line.split(" ");
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < words.length; i++) {
      String[] c = words[i].split("");
      c[0] = c[0].toUpperCase();
      for (int j = 0; j < c.length; j++) {
        sb.append(c[j]);
      }
      sb.append(" ");
    }

    System.out.println("변환된 문자열 : " + sb);
    System.out.println("총 단어 개수 : " + words.length);
  }
}
