package com.wissen;

import java.util.HashMap;
import java.util.Map;

public class DSAlgo {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        //base case
        if(n < 2) {
            return;
        }

        int i=0, j=1;

        while(i < n && j < n) {
            if(nums[i] == 0 && nums[j] != 0) {
                swap(nums, i ,j);
                i++;
                j++;
            }
            else if(nums[i] == 0 && nums[j] == 0) {
                j++;
            } else if(nums[i] != 0 && nums[j] == 0) {
                i++;
                j++;
            } else {
                i++;
                j++;
            }
        }

    }

    private void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] result = new int[n];
        //build the prefix product array
        prefix[0] = 1;
        for(int i = 1; i < n; i++) {
            prefix[i] = nums[i-1] * prefix[i-1];
        }

        //build the suffix product array
        suffix[n-1] = 1;
        for(int i = n - 2; i >= 0; i--) {
            suffix[i] = nums[i+1] * suffix[i+1];
        }

        for(int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> hmap = new HashMap<>();
        hmap.put(0,1);
        int prefixSum = 0;
        int count = 0;

        for(int i=0; i<n; i++) {
            prefixSum += nums[i];
            int temp = prefixSum - k;
            if(hmap.containsKey(temp)) {
                count += hmap.get(temp);
            }
            hmap.put(prefixSum, hmap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
