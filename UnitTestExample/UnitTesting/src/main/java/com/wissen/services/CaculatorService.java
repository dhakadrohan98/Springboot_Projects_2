package com.wissen.services;

public class CaculatorService {
    public int addTwoNum(int a, int b) {
        return a + b;
    }

    public int productTwoNum(int a, int b) {
        return a * b;
    }

    public int divideTwoNum(int a, int b) {
        if(b != 0) {
            return a/b;
        } else {
            System.out.println("Divide by 0 is not invalid");
            return -1;
        }
    }

    public int sumAnyNumbers(int ...numbers) {
        int result = 0;
        for(int n: numbers) {
            result += n;
        }
        return result;
    }
}