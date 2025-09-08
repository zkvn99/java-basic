package com.minuk.chapter4.loop.normal;

import java.util.Scanner;

public class Application2 {

  public static void main(String[] args) {
    /* 반복문을 이용하여 알파벳 소문자 'a'부터 'z'까지를 개행 없이 차례로 출력하세요
     *
     * -- 출력 예시 --
     * abcdefghijklmnopqrstuvwxyz
     * */

    StringBuilder sb = new StringBuilder();
    for (int i = 97; i <= 122; i++) {
      sb.append((char) i);
    }
    System.out.println(sb);
  }
}
