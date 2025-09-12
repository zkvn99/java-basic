package com.minuk.chapter9.basic;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Application2 {

  public static void main(String[] args) {
    /* ----- 입력 예시 -----
     *
     * 문자열 입력 : hello world Hello everyone! 안녕하세요 반갑습니다
     *
     * ----- 출력 예시 -----
     *
     * ===== 단어 빈도 =====
     * hello: 2
     * world: 1
     * everyone: 1
     * 가장 빈도 높은 단어 : hello (2 번)
     */

    Scanner sc = new Scanner(System.in);

    System.out.print("문자열 입력 : ");
    String input = sc.nextLine();

    input = input.toLowerCase();
    input = input.replaceAll("[^a-zA-Z ]", " ");

    String[] words = input.split("\\s+");

    Map<String, Integer> map = new LinkedHashMap<>();

    for (String word : words) {
      map.put(word, map.getOrDefault(word, 0) + 1);
    }

    System.out.println("===== 단어 빈도 =====");
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      sb.append(entry.getKey())
          .append(": ")
          .append(entry.getValue())
          .append("\n");
    }

    String maxWord = null;
    int maxCount = 0;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() > maxCount) {
        maxCount = entry.getValue();
        maxWord = entry.getKey();
      }
    }

    sb.append("가장 빈도 높은 단어 : ")
      .append(maxWord)
      .append(" (")
      .append(maxCount)
      .append(" 번)");

    System.out.println(sb);
  }
}
