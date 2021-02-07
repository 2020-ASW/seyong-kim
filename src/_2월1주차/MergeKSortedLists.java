package _2월1주차;


import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (ListNode node : lists) {
            if (node == null) continue;

            pq.add(node.val);

            ListNode next = node.next;
            while (next != null) {
                pq.add(next.val);
                next = next.next;
            }
        }

        ListNode head = new ListNode();
        ListNode cur = head;

        while (!pq.isEmpty()) {
            cur.val = pq.poll();
            cur.next = (pq.peek() != null ? new ListNode(pq.peek()) : null);
            cur = cur.next;
        }

        if (head.next == null && head.val == 0) return null;

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        ListNode[] list = {node1, node2, node3};
//        mergeKLists(list);
        mergeKLists(new ListNode[]{null});
    }
}
