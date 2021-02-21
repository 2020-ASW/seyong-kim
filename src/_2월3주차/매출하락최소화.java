package _2월3주차;

import java.util.ArrayList;

public class 매출하락최소화 {
    static Node[] employees;
    static int[][] dp;
    static ArrayList<Node>[] tree;

    public static int solution(int[] sales, int[][] links) {
        employees = new Node[sales.length + 1];

        for (int i = 0; i < sales.length; i++) {
            employees[i + 1] = new Node(i + 1, sales[i]);
        }

        tree = new ArrayList[sales.length + 1];
        for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>();

        for (int[] link : links) {
            tree[link[0]].add(employees[link[1]]);
        }

        dp = new int[sales.length + 1][2];
        dfs(new Node(1, 14));

        return Math.min(dp[1][0], dp[1][1]);
    }

    private static void dfs(Node node) {
        for (Node child : tree[node.no]) {
            dfs(child);
        }

        if (tree[node.no].size() == 0) {
            dp[node.no][0] = 0;
            dp[node.no][1] = node.cost;
            return;
        }

        int sum = 0, minCost = Integer.MAX_VALUE;
        boolean flag = false;
        for (Node child : tree[node.no]) {
            sum += Math.min(dp[child.no][0], dp[child.no][1]);

            if (dp[child.no][0] >= dp[child.no][1])
                flag = true;

            minCost = Math.min(minCost, dp[child.no][1] - dp[child.no][0]);
        }
        dp[node.no][1] = node.cost + sum;
        dp[node.no][0] = sum + minCost;

        if (flag)
            dp[node.no][0] = sum;
    }

    static class Node {
        int no, cost;

        Node(int no, int cost) {
            this.no = no;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
        int[][] links = {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}};
        System.out.println(solution(sales, links));
    }
}
