package com.minuk.chapter8.basic;

public class Triangle extends Shape implements Resizable{
  public double base;
  public double height;
  public double side1;
  public double side2;
  public double side3;

  public Triangle(double base, double height, double side1, double side2, double side3) {
    this.base = base;
    this.height = height;
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }


  @Override
  double calculateArea() {
    return (base * height) / 2;
  }

  @Override
  double calculatePerimeter() {
    return side1 + side2 + side3;
  }

  @Override
  public void resize(double factor) {
    this.base = factor * base;
    this.height = factor * height;
    this.side1 = factor * side1;
    this.side2 = factor * side2;
    this.side3 = factor * side3;
  }
}
