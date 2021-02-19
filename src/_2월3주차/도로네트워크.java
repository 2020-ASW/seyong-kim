package _2월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 도로네트워크 {
    private static ArrayList<Node>[] tree;
    private static int[] depth;
    private static final int MAX_N = 5;
    private static final int MAX_D = 3;
    private static int[][] parent;
    private static int[][] maxDP, minDP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        parent = new int[MAX_D + 1][MAX_N + 1];
        maxDP = new int[MAX_D + 1][MAX_N + 1];
        minDP = new int[MAX_D + 1][MAX_N + 1];

        int N = Integer.parseInt(br.readLine().trim());
        tree = new ArrayList[N + 1];
        depth = new int[N + 1];

        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            tree[u].add(new Node(v, dist));
            tree[v].add(new Node(u, dist));
        }

        Arrays.fill(depth, -1);
        dfs(1, 0);

        for (int jump = 1; jump < MAX_D; jump++) {
            for (int i = 1; i <= N; i++) {
                parent[jump][i] = parent[jump - 1][parent[jump - 1][i]];
                minDP[jump][i] = Math.min(minDP[jump - 1][i], minDP[jump - 1][parent[jump - 1][i]]);
                maxDP[jump][i] = Math.max(maxDP[jump - 1][i], maxDP[jump - 1][parent[jump - 1][i]]);
            }
        }

        int M = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int[] result = lca(u, v);
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int node, int level) {
        if (depth[node] != -1)
            return;

        depth[node] = level;
        for (Node next : tree[node]) {
            if (depth[next.no] != -1) continue;

            parent[0][next.no] = node;
            maxDP[0][next.no] = minDP[0][next.no] = next.dist;

            for (int i = 1; i <= MAX_D; i++) {
                if (parent[i - 1][next.no] == 0) break;

                parent[i][next.no] = parent[i - 1][parent[i - 1][next.no]];
            }
            dfs(next.no, level + 1);
        }
    }

    private static int[] lca(int u, int v) {
        int a = u, b = v;
        // b가 항상 더 깊은 노드로
        if (depth[a] > depth[b]) return lca(b, a);

        int[] result = {Math.min(minDP[0][a], minDP[0][b]), Math.max(maxDP[0][a], maxDP[0][b])};
        result[0] = result[0] == 0 ? 1000001 : result[0];

        // 깊이를 맞춰 준다
        for (int i = MAX_D; i >= 0; i--) {
            if (depth[b] - depth[a] >= (1 << i)) {
                result[0] = Math.min(result[0], minDP[i][b]);
                result[1] = Math.max(result[1], maxDP[i][b]);

                b = parent[i][b];
            }
        }

        if (a == b) return result;

        for (int i = MAX_D; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
                result[0] = Math.min(result[0], Math.min(minDP[i][a], minDP[i][b]));
                result[1] = Math.max(result[1], Math.max(maxDP[i][a], maxDP[i][b]));
            }
        }
        return result;
    }

    static class Node {
        int no;
        int dist;

        Node(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }
    }
}
