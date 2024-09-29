package com.wissen.services;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class CaculatorServiceTest {
    CaculatorService caculatorService = new CaculatorService();

    @Test
    public void addTwoNumbersTest() {
        int actual = caculatorService.addTwoNum(45, 12);
        int expected = 57;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void SumAnyNumbersTest() {
        int result = caculatorService.sumAnyNumbers(2,7,8,9);
        int expected = 26;
        Assert.assertEquals(expected, result);
    }
}