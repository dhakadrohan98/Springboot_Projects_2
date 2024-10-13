package com.wissen;
import java.util.*;

public class ProblemSolving {
    //TC: O( n!(permutation) * n(for loop) )
    //SC: O(n) ds + O(n) map + O(n) stack space
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        boolean[] freq = new boolean[n];

        rec(nums, ds, freq, ans);
        return ans;
    }

    private void rec(int[] nums, List<Integer> ds, boolean[] freq, List<List<Integer>> ans) {

        //base case
        if(ds.size() == nums.length) {
            ans.add(new ArrayList<>(ds));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(freq[i] == false) {
                ds.add(nums[i]);
                freq[i] = true;
                //give recursive call for next digit of same permutation
                rec(nums, ds, freq, ans);
                //backtrack from here
                //remove last element from ds & unmarked from ith index from freq array
                ds.remove(ds.size() - 1);
                freq[i] = false;
            }
        }
        return;
    }
}
