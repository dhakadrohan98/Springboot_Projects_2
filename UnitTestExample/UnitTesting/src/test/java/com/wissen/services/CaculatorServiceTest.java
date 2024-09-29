package com.wissen.services;

import junit.framework.TestCase;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.rules.Timeout;

import java.util.Date;

public class CaculatorServiceTest {

    private static CaculatorService caculatorService;
    private static int counter = 0;

    @BeforeClass
    public static void init() {
        caculatorService = new CaculatorService();
        System.out.println("Opening a file/connection");
        System.out.println("Started test class date:" + new Date());
        //if you have opened any connection/file then you have to close that connection/file.
    }

    //Will execute the method before each test case
    @Before
    public void beforeEachTestCase() {
        System.out.println("Before each test case");
        //resetting the counter value
        counter = 0;
    }

    @Ignore("Test is ignored as demonstration")
    @Test
    public void addTwoNumbersTest() {
        for(int i=1; i <= 20; i++) {
            counter += i;
        }
        System.out.println("Test for add two numbers is executing");
        int actual = caculatorService.addTwoNum(45, 12);
        int expected = 57;
        System.out.println("Counter value in addTwoNumbersTest() is: " + counter);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void SumAnyNumbersTest() {
        for(int i=1; i <= 20; i++) {
            counter += i;
        }
        System.out.println("Test for adding any number is executing");
        int result = caculatorService.sumAnyNumbers(2,7,8,9);
        int expected = 26;
        System.out.println("Counter value in SumAnyNumbersTest() is: " + counter);
        Assert.assertEquals(expected, result);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        caculatorService.divideTwoNum(7, 0);
    }

    @Test(timeout = 10)
    public void testTimeout() {
        for(int i=0; i<100000000; i++) {
            for(int j=i; j<100000000; j++) {
//                for(int k=i; k<100000; k++) {
//
//                }
            }
        }
    }


    @AfterClass
    public static void cleanUp() {
        System.out.println("Closing a file/connection");
        System.out.println("Closed test class date:" + new Date());
        caculatorService = null;
    }

    @After
    public void afterEachTestCase() {
        System.out.println("After each test case, do some analysis operation");
    }
}