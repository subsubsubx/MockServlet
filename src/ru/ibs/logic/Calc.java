package ru.ibs.logic;

public class Calc {

    private double a;
    private double b;
    private String op;
    private double res;


    public Calc(double a, double b, String op) {
        this.a = a;
        this.b = b;
        this.op = op;
        getRes();
    }


    private double division(double a, double b) {
        this.a = a;
        this.b = b;
            return a / b;
    }

    private double multiplication(double a, double b) {
        this.a = a;
        this.b = b;
        return a * b;

    }


    private double addition(double a, double b) {
        this.a = a;
        this.b = b;
        return a + b;
    }

    private double subtraction(double a, double b) {
        this.a = a;
        this.b = b;
        return a - b;
    }

    public double getRes() {
        switch (op) {
            case "+":
                res = addition(a, b);
                break;
            case "-":
                res = subtraction(a, b);
                break;
            case "*":
                res = multiplication(a, b);
                break;
            case "/":
                if (!(b == 0)){
                    res = division(a, b);
                    break;
               } else throw new ArithmeticException();
            default:
                System.out.println("Error!");
                break;
        }
        return res;
    }
}
