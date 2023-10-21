package SlidingWindow;

public class SlidingWindowQuestions {


    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println(maxSubarraySum(arr, k));
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
}
