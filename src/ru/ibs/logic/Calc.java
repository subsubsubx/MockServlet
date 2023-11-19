package ru.ibs.logic;

public class Calc {

    private int a;
    private int b;
    private String op;
    private int res;

    public Calc() {
    }

    public Calc(int a, int b, String op) {
        this.a = a;
        this.b = b;
        this.op = op;
        getRes();
    }


    public int division(int a, int b) {
        this.a = a;
        this.b = b;
            return a / b;
    }

    public int multiplication(int a, int b) {
        this.a = a;
        this.b = b;
        return a * b;

    }


    public int addition(int a, int b) {
        this.a = a;
        this.b = b;
        return a + b;
    }

    public int subtraction(int a, int b) {
        this.a = a;
        this.b = b;
        return a - b;
    }

    public int getRes() {
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
                res = division(a, b);
            default:
                System.out.println("Error!");
                break;
        }
        return res;
    }
}
