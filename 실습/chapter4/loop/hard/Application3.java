package com.minuk.chapter4.loop.hard;

import java.util.Scanner;

public class Application3 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("문자열 입력 : ");
    String line = input.nextLine();
    boolean flag = false;

    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) > 90 && line.charAt(i) < 97) {
        flag = true;
      } else if (line.charAt(i) < 65) {
        flag = true;
      } else if (line.charAt(i) > 122) {
        flag = true;
      }

      if (flag) {
        System.out.println("영문자가 아닌 문자가 포함되어 있습니다.");
        return;
      }
    }

    System.out.print("문자 입력 : ");
    String keyword = input.next();

    int count = 0;
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == keyword.charAt(0)) {
        count++;
      }
    }

    System.out.println("포함된 갯수 : " + count + "개");
  }
}
