package _2월2주차;

import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] row : matrix){
            for(int x : row){
                pq.add(x);
            }
        }
        int idx = 1;
        while(!pq.isEmpty()){
            int result = pq.poll();
            if(idx++ == k) return result;
        }
        return 0;
    }
}
