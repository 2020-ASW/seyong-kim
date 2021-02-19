package _2월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LCA2 {
    private static ArrayList<Integer>[] con;
    private static int[] tree;
    private static final int MAX_N = 100000;
    private static final int MAX_D = 17;
    private static int[][] par;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        par = new int[MAX_D + 1][MAX_N + 1];

        int N = Integer.parseInt(br.readLine().trim());
        con = new ArrayList[N + 1];
        tree = new int[N + 1];

        for (int i = 1; i <= N; i++) con[i] = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            con[u].add(v);
            con[v].add(u);
        }

        Arrays.fill(tree, -1);

        dfs(1, 0);

        int M = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(lca(u, v))
                    .append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int node, int depth) {
        if (tree[node] != -1)
            return;

        tree[node] = depth;
        for (int next : con[node]) {
            if (tree[next] != -1) continue;

            par[0][next] = node;

            for (int i = 1; i <= MAX_D; i++) {
                if (par[i - 1][next] == 0) break;

                par[i][next] = par[i - 1][par[i - 1][next]];
            }
            dfs(next, depth + 1);
        }
    }

    private static int lca(int a, int b) {
        // b가 항상 더 깊은 노드로
        if (tree[a] > tree[b])
            return lca(b, a);

        // 깊이를 맞춰 준다
        for (int i = MAX_D; i >= 0; i--) {
            if (tree[b] - tree[a] >= (1 << i))
                b = par[i][b];
        }

        if (a == b) return a;

        for (int i = MAX_D; i >= 0; i--) {
            if (par[i][a] != par[i][b]) {
                a = par[i][a];
                b = par[i][b];
            }
        }
        return par[0][a];
    }
}
