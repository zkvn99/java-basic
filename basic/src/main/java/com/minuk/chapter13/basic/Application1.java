package com.minuk.chapter13.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application1 {

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    List<Integer> list = new ArrayList<>();
    int sum = 0;

    while (true) {
      System.out.print("학생 성적 : ");
      list.add(Integer.parseInt(input.nextLine()));

      System.out.print("추가 입력하려면 : ");
      String chk = input.nextLine();

      if (chk.equals("n")) {
        for (Integer i : list) {
          sum += i;
        }
        System.out.println("학생 인원 : " + list.size());
        System.out.println("평균 점수 : " + sum / (float) list.size());
        return;
      }
    }

  }
}
