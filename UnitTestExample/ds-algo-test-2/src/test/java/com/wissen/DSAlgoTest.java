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

    @AfterClass
    public static void destroyObject() {
        dsAlgo = null;
    }

}
