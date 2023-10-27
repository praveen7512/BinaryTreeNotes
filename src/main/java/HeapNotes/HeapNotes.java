package HeapNotes;

import java.util.*;

public class HeapNotes {


    public static void main(String[] args) {

//        List<List<Integer>> nums = new ArrayList<>();
//
//        // Define some sorted arrays
//        List<Integer> arr1 = Arrays.asList(1, 3, 5);
//        List<Integer> arr2 = Arrays.asList(2, 4, 6);
//        List<Integer> arr3 = Arrays.asList(0, 7, 8);
//
//        // Add the sorted arrays to the list
//        nums.add(arr1);
//        nums.add(arr2);
//        nums.add(arr3);
//
//        int k = 3; // Number of arrays to merge
//
//        List<Integer> merged = mergeKSortedArrays(nums, k);
//
//        // Print the merged array
//        System.out.println("Merged Array: " + merged);

        //Input: nums = [1,1,1,2,2,3], k = 2
        //Output: [1,2]

        int ans[] = topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);

        System.out.println(Arrays.toString(ans));

    }

    public static int kthLargest(int[] heap_array, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(heap_array[i]);
        }

        for (int i = k; i < heap_array.length; i++) {

            if (priorityQueue.peek() < heap_array[i]) {

                priorityQueue.poll();
                priorityQueue.offer(heap_array[i]);
            }
        }

        int ans = priorityQueue.peek();
        return ans;


    }


    public static List<Integer> mergeKSortedArrays(List<List<Integer>> nums, int k) {
        if (nums.isEmpty() || k <= 0 || k > nums.size()) {
            return new ArrayList<>(); // Return an empty list for invalid inputs.
        }

        PriorityQueue<K_sortedArrayHelper> heap = new PriorityQueue<>();
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            List<Integer> array = nums.get(i);
            if (!array.isEmpty()) {
                heap.offer(new K_sortedArrayHelper(array.get(0), i, 0));
            }
        }

        while (!heap.isEmpty()) {
            K_sortedArrayHelper curr = heap.poll();
            ans.add(curr.element);
            int nextIndex = curr.index + 1;
            if (nextIndex < nums.get(curr.belong_array).size()) {
                heap.offer(new K_sortedArrayHelper(nums.get(curr.belong_array).get(nextIndex), curr.belong_array, nextIndex));
            }
        }

        return ans;
    }

    static class K_sortedArrayHelper implements Comparable<K_sortedArrayHelper> {
        int element;
        int belong_array;
        int index;

        public K_sortedArrayHelper(int element, int belong_array, int index) {
            this.element = element;
            this.belong_array = belong_array;
            this.index = index;
        }

        @Override
        public int compareTo(K_sortedArrayHelper o) {
            return this.element - o.element;
        }
    }

    public static int minStoneSum(int[] piles, int k) {
        //first step -> sabko heap mein daalo


        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        int sum = 0;

        for (int pile : piles) {
            maxHeap.offer(pile);
        }

        // second step -> o to k loop chalo and kam karo

        for (int i = 0; i < k; i++) {
            int curr = maxHeap.poll();
            int element = (int) (curr - Math.floor(curr / 2));
            maxHeap.offer(element);
        }

        for (Integer integer : maxHeap) {
            sum = sum + integer;
        }
        // sum print karo
        return sum;
    }


    public static int[] topKFrequent(int[] nums, int k) {

        int[] ans = new int[k];
        PriorityQueue<TopKFrequent<Integer>> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        hashMap.forEach((integer, integer2) -> maxheap.offer(new TopKFrequent<Integer>(integer, integer2)));

        for (int i = 0; i < k; i++) {
            TopKFrequent curr = maxheap.poll();
            ans[i] =(int) curr.value;
        }
        return ans;


    }



    static class TopKFrequent<T> implements Comparable<TopKFrequent> {
        T value;
        int frequency;

        public TopKFrequent(T value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(TopKFrequent o) {
            return this.frequency - o.frequency;
        }
    }


}
