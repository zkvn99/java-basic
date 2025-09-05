package com.minuk.level03.normal;

import java.util.Random;

public class RandomMaker {

  public int randomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min + 1) + min;
  }

  public String randomUpperAlpabet(int length) {
    Random random = new Random();
    String randomString = "";
    for (int i = 0; i < length; i++) {
      char c = (char) (random.nextInt(26) + 65);
      randomString += c;
    }
    return randomString;
  }

  public String rockPaperScissors() {
    String[] rps = {"Paper", "Scissors", "Rock"};
    Random random = new Random();
    return rps[random.nextInt(3)];
  }

  public String tossCoin() {
    String[] coin = {"앞", "뒤"};
    Random random = new Random();
    return coin[random.nextInt(2)];
  }

}
