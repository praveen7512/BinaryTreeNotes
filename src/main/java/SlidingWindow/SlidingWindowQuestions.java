package SlidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindowQuestions {


    public static void main(String[] args) {

        System.out.println(containPermutation("ab", "afbjskdlabfha"));

    }


    public static int maxSubarraySum(int[] arr, int k) {
        if (k <= 0 || k > arr.length) {
            return -1; // Invalid input
        }

        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }
        maxSum = currentSum;

        for (int i = k; i < arr.length; i++) {
            currentSum = (currentSum - arr[i - k]) + arr[i];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static List<Integer> findAnagrams(String s, String p) {
        if (p.length() == 0 || s.length() == 0) return new ArrayList<>();
        int windowSize = p.length();
        StringBuilder window = new StringBuilder("");
        List<Integer> ans_list = new ArrayList<>();

        for (int i = 0; i < windowSize; i++) {
            window.append(s.charAt(i));
        }
        if (isAnagram(window.toString(), p)) {
            ans_list.add(0);
        }
        for (int i = windowSize; i < s.length(); i++) {

            window.deleteCharAt(0);
            window.append(s.charAt(i));
            boolean ans = isAnagram(window.toString(), p);
            if (ans) ans_list.add(i - windowSize + 1);


        }
        return ans_list;
    }

    public static boolean isAnagram(String s, String p) {
        // Check if the lengths of the two strings are different
        if (s.length() != p.length()) {
            return false; // They can't be anagrams if the lengths are different.
        }

        // Convert the strings to character arrays
        char[] sArray = s.toCharArray();
        char[] pArray = p.toCharArray();

        // Sort the character arrays
        Arrays.sort(sArray);
        Arrays.sort(pArray);

        // Compare the sorted arrays to check if they are equal
        return Arrays.equals(sArray, pArray);
    }

    public static boolean containPermutation(String s1, String s2) {
        if(s1.length()>s2.length()) return false;
        StringBuilder window = new StringBuilder("");

        for (int i = 0; i < s1.length(); i++) {
            window.append(s2.charAt(i));
        }

        if (isAnagram(s1, window.toString())) return true;

        for (int i = window.length(); i < s2.length(); i++) {
            window.deleteCharAt(0);
            window.append(s2.charAt(i));
            if (isAnagram(window.toString(), s1)) return true;
        }
        return false;


    }


}
