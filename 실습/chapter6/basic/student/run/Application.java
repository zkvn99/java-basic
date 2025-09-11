package com.minuk.chapter6.basic.student.run;

import com.minuk.chapter6.basic.student.model.dto.StudentDTO;
import java.util.Scanner;

public class Application {

  public static void main(String[] args) {
    // 최대 10명의 학생 정보를 기록할 수 있게 배열을 할당한다.
    // while문을 사용하여 학생들의 정보를 계속 입력 받고
    // 한 명씩 추가 될 때마다 카운트함
    // 계속 추가할 것인지 물어보고, 대소문자 상관없이 ‘y’이면 계속 객체 추가

    // 3명 정도의 학생 정보를 입력 받아 각 객체에 저장함
    // 현재 기록된 학생들의 각각의 점수 평균을 구함
    // 학생들의 정보를 모두 출력 (평균 포함)

    StudentDTO[] studentDTO = new StudentDTO[10];
    Scanner input = new Scanner(System.in);
    int count = 0;

    while (count < studentDTO.length) {
      System.out.print("학년 : ");
      int grade = input.nextInt();

      System.out.print("반 : ");
      int classroom = input.nextInt();

      System.out.print("이름 : ");
      String name = input.next();

      System.out.print("국어점수 : ");
      int kor = input.nextInt();

      System.out.print("영어점수 : ");
      int eng = input.nextInt();

      System.out.print("수학점수 : ");
      int math = input.nextInt();

      System.out.print("계속 추가할 겁니까 ? (y/n) : ");
      String answer = input.next();

      studentDTO[count++] = new StudentDTO(grade, classroom, name, kor, eng, math);

      if (answer.equals("n")) {
        break;
      }
    }

    for (int i = 0; i < count; i++) {
      System.out.println(studentDTO[i].getInformation());
    }
  }
}
