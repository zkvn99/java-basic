package com.minuk.chapter11.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Application2 {

  public static void main(String[] args) {
    /* ----- 입력 예시 -----
     *
     * (원본 파일이 존재 하는 경우)
     * 원본 파일의 이름을 입력하세요 : origin.txt
     * 복사 파일의 이름을 입력하세요 : copy.txt
     *
     * ----- 출력 예시 -----
     *
     * 파일 복사가 성공적으로 완료 되었습니다.
     *
     * ----- 입력 예시 -----
     *
     * (원본 파일이 존재하지 않는 경우)
     * 원본 파일의 이름을 입력하세요 : origin2.txt
     * 복사 파일의 이름을 입력하세요 : copy2.txt
     *
     * ----- 출력 예시 -----
     *
     * 오류 : origin2.txt (지정된 파일을 찾을 수 없습니다)
     *
     */

    Scanner input = new Scanner(System.in);

    System.out.print("원본 파일의 이름을 입력하세요 : ");
    String originName = input.nextLine();
    System.out.print("복사 파일의 이름을 입력하세요 : ");
    String copyName = input.nextLine();
    StringBuilder sb = new StringBuilder();

    try (BufferedReader br = new BufferedReader(new FileReader(originName))) {
      String temp;
      while ((temp = br.readLine()) != null) {
        sb.append(temp).append("\n");
      }
    } catch (FileNotFoundException e) {
      System.out.println("지정된 파일을 찾을 수 없습니다");
      return;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return;
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(copyName))) {
      bw.write(sb.toString().trim());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
