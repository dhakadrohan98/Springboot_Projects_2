package com.wissen;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProblemSolvingTest {

    private static ProblemSolving problemSolving;

    @BeforeClass
    public static void init() {
        problemSolving = new ProblemSolving();
    }

    @Test
    public void testPermute() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> expected = List.of(
                List.of(1, 2, 3),
                List.of(1, 3, 2),
                List.of(2, 1, 3),
                List.of(2, 3, 1),
                List.of(3, 1, 2),
                List.of(3, 2, 1)
        );

        List<List<Integer>> actual = problemSolving.permute(nums);

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }

    @AfterClass
    public static void destroy() {
        problemSolving = null;
        System.gc();
    }
}
