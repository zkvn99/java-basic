package com.minuk.chapter13.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Application5 {

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    List<String> words = new ArrayList<>();

    while (true) {
      System.out.print("단어 입력 ('exit' 입력 시 종료): ");
      String name = input.nextLine();

      if (name.equals("exit")) {
        System.out.println("정렬 된 단어 : " + words);
        return;
      } else {
        words.add(name);
        Collections.sort(words);
      }
    }
  }
}
