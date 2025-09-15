package com.minuk.chapter10.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Application2 {

  public static void main(String[] args) {
    /* ----- 입력 예시 -----
     *
     * 생년월일 입력 (yyyy-MM-dd 양식으로 작성) : 2014-05-05
     *
     * ----- 출력 예시 -----
     *
     * 만 20세 미만은 입장 불가입니다.
     *
     * ----- 입력 예시 -----
     *
     * 생년월일 입력 (yyyy-MM-dd 양식으로 작성) : 1994-05-05
     *
     * ----- 출력 예시 -----
     *
     * 입장하셔도 됩니다.
     *
     * ----- 입력 예시 -----
     *
     * 생년월일 입력 (yyyy-MM-dd 양식으로 작성) : 2000-14-15
     *
     * ----- 출력 예시 -----
     *
     * 날짜 양식을 잘못 입력하셨습니다.
     */

    System.out.print("생년월일 입력 (yyyy-MM-dd 양식으로 작성) : ");

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input = br.readLine();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate date = LocalDate.parse(input, formatter);
      LocalDate now = LocalDate.now();

      long age = ChronoUnit.YEARS.between(date, now);

      if (age < 20) {
        throw new InvalidAgeException("만 20세 미만은 입장 불가입니다.");
      }
    } catch (DateTimeParseException e) {
      System.out.println("날짜 양식을 잘못 입력하셨습니다.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
