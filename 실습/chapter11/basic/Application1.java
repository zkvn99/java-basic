package com.minuk.chapter11.basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Application1 {

  public static void main(String[] args) {
    /* ----- 입력 예시 -----
     *
     * (존재하는 파일명을 입력한 경우)
     * 파일 이름을 입력하세요 : hello.txt
     *
     * ----- 출력 예시 -----
     *
     * ===== 파일 내용 출력 =====
     * 안녕하세요. 반갑습니다. 제 이름은 홍길동입니다.
     *
     * ----- 입력 예시 -----
     *
     * (존재하지 않는 파일명을 입력한 경우)
     * 파일 이름을 입력하세요 : unknown.txt
     *
     * ----- 출력 예시 -----
     *
     * 오류 : 해당 이름을 가진 파일이 없습니다.
     *
     */

    Scanner sc = new Scanner(System.in);
    System.out.print("파일 이름을 입력하세요 : ");
    String fileName = sc.nextLine();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String temp;
      while ((temp = br.readLine()) != null) {
        System.out.println(temp);
      }
    } catch (FileNotFoundException e) {
      System.out.println("오류 : 해당 이름을 가진 파일이 없습니다.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
