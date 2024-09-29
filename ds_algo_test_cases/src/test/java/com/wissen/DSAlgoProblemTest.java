package com.wissen;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DSAlgoProblemTest {

    private final DSAlgoProblem obj = new DSAlgoProblem();

    //Q.1 maximum sum subarray
    @Test
    void testMaxSubArray() {
        //test case 1
        int[] nums1 = {-2,-3,4,-1,-2,1,5,-3}; //7
        assertEquals(7, obj.maxSubArray(nums1));
        //test case 2
        int[] nums2 = {-2,1,-3,4,-1,2,1,-5,4}; //6
        assertEquals(6, obj.maxSubArray(nums2));
    }

    //Q.2 merge two sorted array
    @Test
    void testMerge() {
        //test case 1
        int[] nums1 = {1,2,3,0,0,0};
        int m  = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        int[] result1 = {1,2,2,3,5,6};
        obj.merge(nums1, m, nums2, n);
        assertArrayEquals(result1, nums1);
        //test case 2
        int[] nums3 = {7,8,9,0,0,0};
        int m1  = 3;
        int[] nums4 = {2,5,6};
        int n1 = 3;
        int[] result2 = {2,5,6,7,8,9};
        obj.merge(nums3, m, nums4, n);
        assertArrayEquals(result2, nums3);
    }

    //Q.3 Product of array except itself
    @Test
    void testProductExceptSelf() {
        //test case 1
        int[] nums1 = {1,2,3,4};
        int[] result1 = {24,12,8,6};
        assertArrayEquals(result1, obj.productExceptSelf(nums1));

        //test case 2
        int[] nums2 = {-1,1,0,-3,3};
        int[] result2 = {0,0,9,0,0};
        assertArrayEquals(result2, obj.productExceptSelf(nums2));
    }

}
