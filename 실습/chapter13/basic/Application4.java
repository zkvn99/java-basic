package com.minuk.chapter13.basic;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Application4 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Set<String> names = new HashSet<>();

    while (true) {
      System.out.print("학생 ID 입력('exit' 입력 시 종료): ");
      String name = input.nextLine();

      if (name.equals("exit")) {
        System.out.println("모든 학생의 ID : " + names);
        return;
      } else if (names.contains(name)) {
        System.out.println("이미 등록 된 ID입니다.");
      } else {
        names.add(name);
        System.out.println("ID가 추가 되었습니다.");
      }
    }
  }
}
