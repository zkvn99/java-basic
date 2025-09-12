package com.minuk.chapter8.basic;

public class Circle extends Shape implements Resizable {
  private double radius;

  public Circle(int radius) {
    this.radius = radius;
  }

  @Override
  double calculateArea() {
    return Math.PI * Math.pow(radius, 2);
  }

  @Override
  double calculatePerimeter() {
    return radius * 2 * Math.PI;
  }

  @Override
  public void resize(double factor) {
    this.radius = factor * radius;
  }
}
