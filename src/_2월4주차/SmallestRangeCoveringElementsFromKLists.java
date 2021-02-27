package _2월4주차;

import java.util.List;
import java.util.PriorityQueue;
/**
 * MinHeap을 이용한 방법.
 * max는 변수로 갱신하고 최소값은 minHeap을 통해 유지하면서
 * 최소 범위를 구한다
 * */
public class SmallestRangeCoveringElementsFromKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Element> pq = new PriorityQueue<>();

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            Element e = new Element(i, 0, nums.get(i).get(0));
            pq.offer(e);
            max = Math.max(max, nums.get(i).get(0));
        }

        int range = Integer.MAX_VALUE;
        int start = -1, end = -1;

        while (pq.size() == nums.size()) {
            Element cur = pq.poll();

            if (max - cur.val < range) {
                range = max - cur.val;
                start = cur.val;
                end = max;
            }

            if (cur.idx + 1 < nums.get(cur.row).size()) {
                int val = nums.get(cur.row).get(cur.idx + 1);
                Element e = new Element(cur.row, cur.idx + 1, val);
                pq.offer(e);

                if (e.val > max) max = e.val;
            }
        }

        return new int[]{start, end};
    }

    static class Element implements Comparable<Element> {
        int row;
        int idx;
        int val;

        public Element(int row, int idx, int val) {
            this.row = row;
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(Element o) {
            return this.val - o.val;
        }
    }
}
