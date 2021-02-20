package _2월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 뉴스전하기 {
    static int N;
    static ArrayList<Info>[] tree;


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

            tree[superior].add(new Info(employee, 0));
            employee++;
        }

        System.out.println(dfs(0));
    }

    private static int dfs(int employee) {
        int max = 0, t = 0;

        for (Info info : tree[employee]) {
            info.time = dfs(info.node);
        }

        Collections.sort(tree[employee]);

        for (Info next : tree[employee])
            max = Math.max(max, next.time + (++t));

        return max;
    }

    static class Info implements Comparable<Info> {
        int node;
        int time;

        Info(int node, int time) {
            this.node = node;
            this.time = time;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(o.time, this.time);
        }
    }
}
/*
12
-1 0 0 1 1 2 3 4 4 6 9 10
*/

