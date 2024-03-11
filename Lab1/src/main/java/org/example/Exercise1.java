package org.example;

import java.util.Scanner;
public class Exercise1 {

    public static void main(String[] args) {

        System.out.println("Complex Number Calculator");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");

        System.out.print("Enter your choice (1/2/3): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        ComplexNumber result;

        switch (choice) {
            case 1: {
                System.out.println("Enter the real part of the first complex number: ");
                double r1 = scanner.nextDouble();
                System.out.println("Enter the imaginary part of the first complex number: ");
                double i1 = scanner.nextDouble();

                System.out.println("Enter the real part of the second complex number: ");
                double r2 = scanner.nextDouble();
                System.out.println("Enter the imaginary part of the second complex number: ");
                double i2 = scanner.nextDouble();

                ComplexNumber num1 = new ComplexNumber(r1,i1);
                ComplexNumber num2 = new ComplexNumber(r2,i2);
                result = num1.add(num2);
                System.out.println("Addition: " + result);
                break;
            }
            case 2: {
                System.out.println("Enter the real part of the first complex number: ");
                double r1 = scanner.nextDouble();
                System.out.println("Enter the imaginary part of the first complex number: ");
                double i1 = scanner.nextDouble();

                System.out.println("Enter the real part of the second complex number: ");
                double r2 = scanner.nextDouble();
                System.out.println("Enter the imaginary part of the second complex number: ");
                double i2 = scanner.nextDouble();

                ComplexNumber num1 = new ComplexNumber(r1,i1);
                ComplexNumber num2 = new ComplexNumber(r2,i2);
                result = num1.subtract(num2);
                System.out.println("Subtraction: " + result);
                break;
            }
            case 3: {
                System.out.println("Enter the real part of the first complex number: ");
                double r1 = scanner.nextDouble();
                System.out.println("Enter the imaginary part of the first complex number: ");
                double i1 = scanner.nextDouble();

                System.out.println("Enter the real part of the second complex number: ");
                double r2 = scanner.nextDouble();
                System.out.println("Enter the imaginary part of the second complex number: ");
                double i2 = scanner.nextDouble();

                ComplexNumber num1 = new ComplexNumber(r1,i1);
                ComplexNumber num2 = new ComplexNumber(r2,i2);
                result = num1.multiply(num2);
                System.out.println("Multiplication: " + result);
                break;
            }
            default:
                System.out.println("Invalid choice");
        }
    }

    static class ComplexNumber {
        private double real;
        private double imaginary;

        public ComplexNumber(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        public ComplexNumber add(ComplexNumber nr) {
            return new ComplexNumber(this.real + nr.real, this.imaginary + nr.imaginary);
        }

        public ComplexNumber subtract(ComplexNumber nr) {
            return new ComplexNumber(this.real - nr.real, this.imaginary - nr.imaginary);
        }

        public ComplexNumber multiply(ComplexNumber nr) {
            double realPart = this.real * nr.real - this.imaginary * nr.imaginary;
            double imaginaryPart = this.real * nr.imaginary + this.imaginary * nr.real;
            return new ComplexNumber(realPart, imaginaryPart);
        }

        @Override
        public String toString() {
            return real + (imaginary >= 0 ? "+" : "") + imaginary + "i";
        }
    }
}