package com.minuk.chapter16;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application3 {

  public static void main(String[] args) {

    List<Student> students = Arrays.asList(
        new Student("철수", "수학", 95),
        new Student("철수", "영어", 80),
        new Student("영희", "수학", 90),
        new Student("영희", "영어", 91),
        new Student("민수", "수학", 85),
        new Student("민수", "영어", 88)
    );

    // 1. 각 학생의 평균 점수를 계산하여 맵으로 반환
    Map<String, Double> averageScoreByStudent =
        students.stream()
                .collect(
                    Collectors.groupingBy(
                        Student::getName,
                        Collectors.averagingInt(Student::getScore)
                    )
                );// 코드 작성

    // 2. 특정 과목(수학)에서 90점 이상 받은 학생의 이름을 리스트로 반환
    List<String> highScorersInMath =
        students.stream()
                .filter(s ->
                            (s.getScore() >= 90) && s.getSubject().equals("수학"))
                .map(Student::getName)
                .toList();// 코드 작성

    // 3. 가장 높은 평균 점수를 가진 학생의 이름을 반환
    String topStudent =
        students.stream()
                .collect(Collectors.groupingBy(
                    Student::getName,
                    Collectors.averagingInt(Student::getScore)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();

    System.out.println(averageScoreByStudent); // 출력 예시: {철수=87.5, 영희=90.5, 민수=86.5}
    System.out.println(highScorersInMath); // 출력 예시: ["철수", "영희"]
    System.out.println(topStudent); // 출력 예시: "영희"

  }
}
