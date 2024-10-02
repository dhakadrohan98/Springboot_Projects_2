package com.wissen;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DSAlgoTest {

    private static DSAlgo dsAlgo;

    @BeforeClass
    public static void createObject() {
        dsAlgo = new DSAlgo();
    }

    @Test
    public void moveZeroesTest() {
        //Test case 1
        int[] nums = {0,1,0,3,12};
        int[] expected = new int[]{1,3,12,0,0};
        dsAlgo.moveZeroes(nums);
        Assert.assertArrayEquals(expected, nums);
        int[] expected2 = new int[]{1,3,12,0,0};
        Assert.assertArrayEquals(expected2, nums);
    }

    @Test
    public void productExceptSelfTest() {
        int[] nums = {-1,1,0,-3,3};
        int[] expected = {0,0,9,0,0};
        int[] result = dsAlgo.productExceptSelf(nums);
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void subarraySumTest() {
        int[] nums = {1,2,3,-3,1,1,1,4,2,-3};
        int k = 3;
        int result = dsAlgo.subarraySum(nums, k);
        int expected = 8;
        Assert.assertEquals(expected, result);
    }

    @AfterClass
    public static void destroyObject() {
        dsAlgo = null;
    }

}
