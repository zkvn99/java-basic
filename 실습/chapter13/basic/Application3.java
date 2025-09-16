package com.minuk.chapter13.basic;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Application3 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Queue<String> q = new ArrayDeque<>();

    while (true) {
      System.out.print("대기 고객 이름 입력 (다음 고객으로 넘어가려면 'next', 종료하려면 'exit'): ");
      String name  = input.nextLine();
      if (name.equals("next")) {
        if (q.isEmpty()) {
          System.out.println("대기 고객이 없습니다.");
        } else {
          System.out.println(q.poll() + " 고객님 차례입니다.");
        }
      } else if (name.equals("exit")) {
        return;
      } else {
        q.offer(name);
        System.out.println(name + " 고객님 대기 등록 되었습니다.");
      }
    }
  }
}
