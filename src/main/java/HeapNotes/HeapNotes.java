package HeapNotes;

import java.util.PriorityQueue;

public class HeapNotes {


    public static void main(String[] args) {

        int[] ans = {1,2,3,4,5};
        System.out.println(kthLargest(ans, 2));

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

        int ans= priorityQueue.peek();
        return ans;


    }
}
