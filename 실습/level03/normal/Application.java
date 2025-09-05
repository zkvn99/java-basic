package com.minuk.level03.normal;

public class Application {

  public static void main(String[] args) {
    RandomMaker maker = new RandomMaker();
    System.out.println(maker.randomNumber(-100, 100));
    System.out.println(maker.randomUpperAlpabet(10));
    System.out.println(maker.rockPaperScissors());
    System.out.println(maker.tossCoin());
  }
}
