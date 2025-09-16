package com.minuk.chapter13.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application6 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Map<String, String> map = new HashMap<>();

    while (true) {
      System.out.print("이름과 전화번호를 띄어쓰기 기준으로 입력하세요 (검색하려면 'search', 종료하려면 'exit'): ");
      String line = input.nextLine();

      if (line.equals("exit")) {
        return;
      } else if (line.equals("search")) {
        System.out.print("검색 할 이름 : ");
        String searchName = input.nextLine();
        String phoneNumber = map.getOrDefault(searchName, "없음");
        if (phoneNumber.equals("없음")) {
          System.out.println(searchName + "씨의 번호는 등록 되어 있지 않습니다.");
        } else {
          System.out.println(searchName + "씨의 전화번호 : " + phoneNumber);
        }
      } else {
        String[] words = line.split(" ");
        if (words.length == 2) {
          map.put(words[0], words[1]);
          System.out.println("추가 완료 : " + words[0] + " " + words[1]);
        } else {
          System.out.println("입력이 잘못 되었습니다. 다음 양식으로 입력해주세요 : <이름> <전화번호>");
        }
      }
    }
  }
}
