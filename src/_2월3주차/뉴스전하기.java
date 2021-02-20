package _2월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 뉴스전하기 {
    static int N;
    static int[] dp;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int employee = 0;
        while (st.hasMoreTokens()) {
            int superior = Integer.parseInt(st.nextToken());

            if (superior == -1) {
                employee++;
                continue;
            }

            tree[superior].add(employee);
            employee++;
        }

        dp = new int[N];
        System.out.println(dfs(0));
    }

    private static int dfs(int employee) {
        if (tree[employee].size() == 0) return 1;

        int children = 1;
        for (int e : tree[employee]) {
            children += dfs(e);
        }
        dp[employee] = children;

        int res = 0;
        for (int e : tree[employee]) {
            res = Math.max(res, dp[e]);
        }

        return res;
    }
}
