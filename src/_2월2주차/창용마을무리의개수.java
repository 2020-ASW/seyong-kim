package _2월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 창용마을무리의개수 {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= TC; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) parent[i] = i;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                union(u, v);
            }
            System.out.println(Arrays.toString(parent));
            Set<Integer> count = new HashSet<>();
            for (int i = 1; i < parent.length; i++) count.add(parent[i]);

            sb.append("#").append(testCase).append(" ").append(count.size()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        parent[y] = x;
        parent[x] = x;

    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }
}

/*
1 2 3 4 5 6
1 1 1 1 1 3
*/
