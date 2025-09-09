package com.minuk.chapter5.oneDimensionalArray.advanced;

import java.util.Arrays;

public class Application1 {

  public static void main(String[] args) {
    /* 로또번호 생성기
     * 6칸 짜리 정수 배열을 하나 생성하고
     * 1부터 45까지의 중복되지 않는 난수를 발생시켜 각 인덱스에 대입한 뒤
     * 오름차순 정렬하여 출력하세요.
     * Arrays.sort(배열) 메소드 활용하여 정렬 가능.
     * */

    int[] numbers = new int[6];
    Arrays.fill(numbers, -1);

    for (int i = 0; i < numbers.length; i++) {
      while (true) {
        int random = (int) (Math.random() * 45) + 1;
        boolean isDuplicated = false;
        for (int j = 0; j < numbers.length; j++) {
          if (numbers[j] == random) {
            isDuplicated = true;
          }
        }

        if (!isDuplicated) {
          numbers[i] = random;
          break;
        }
      }
    }

    Arrays.sort(numbers);

    System.out.println(Arrays.toString(numbers));
  }
}
