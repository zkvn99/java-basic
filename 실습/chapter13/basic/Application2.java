package com.minuk.chapter13.basic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Application2 {

  public static void main(String[] args) {

    Deque<String> q = new ArrayDeque<>();
    Scanner input = new Scanner(System.in);

    while (true) {
      System.out.print("방문 URL을 입력하세요 (단, exit를 입력하면 종료) : ");
      String url = input.nextLine();

      if (url.equals("exit")) {
        return;
      }

      q.addFirst(url);

      if (q.size() == 6) {
        q.removeLast();
      }

      System.out.println("최근 방문 url : " + q);
    }
  }
}
