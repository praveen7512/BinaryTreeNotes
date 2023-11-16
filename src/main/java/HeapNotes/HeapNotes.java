package HeapNotes;

import java.lang.reflect.Array;
import java.util.*;

public class HeapNotes {


    public static void main(String[] args) {

        int[][] points = {{3, 3}, {-2, 4}, {5, -1}};
        int k = 2;

        for (int[] ints : kClosest(points, k)) {
            System.out.println(Arrays.toString(ints));
        }
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
            ans[i] = (int) curr.value;
        }
        return ans;


    }

    public static String sortCharacterByFrequency(String s) {

        HashMap<Character, Integer> hashMap = new HashMap<>();
        PriorityQueue<TopKFrequent<Character>> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder ans = new StringBuilder("");

        for (char i : s.toCharArray()) {
            hashMap.put(i, hashMap.getOrDefault(i, 0) + 1);
        }
        hashMap.forEach((character, integer) -> {
            maxheap.offer(new TopKFrequent<Character>(character, integer));
        });

        while (!maxheap.isEmpty()) {
            TopKFrequent<Character> curr = maxheap.poll();
            for (int i = 0; i < curr.frequency; i++) {
                ans.append(curr.value);
            }

        }
        return ans.toString();

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

    public static boolean isSubWord(String input, String subword) {
        int i = 0, j = 0;

        while (i < input.length() && j < subword.length()) {
            if (input.charAt(i) == subword.charAt(j)) {
                j++;
            }
            i++;
        }

        return (j == subword.length());
    }

    public static int[][] kClosest(int[][] points, int k) {

        PriorityQueue<KClosest> minHeap = new PriorityQueue<>();
        int[][] ans = new int[k][2];

        for (int[] point : points) {
            int distance = 0;
            for (int i : point) {
                distance += i * i;
            }
            minHeap.offer(new KClosest(point, (distance)));
        }

        for (int i = 0; i < k; i++) {

            ans[i] = minHeap.poll().list;
        }

        return ans;

    }

    static class KClosest implements Comparable<KClosest> {
        int[] list;
        int distance;

        public KClosest(int[] list, int distance) {
            this.list = list;
            this.distance = distance;
        }

        @Override
        public int compareTo(KClosest o) {
            return this.distance - o.distance;
        }
    }


}
