package _1월2주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 우주탐사선 {
    static int[][] map;
    static int N;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 플로이드와샬
        for (int k = 0; k < N; k++) {                   // 경우 정점
            for (int i = 0; i < N; i++) {               // 시작 정점
                for (int j = 0; j < N; j++) {           // 도착 정점
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        boolean[] visited = new boolean[N];
        visited[start] = true;
        dfs(visited, start, 0, 0);

        System.out.println(ans);
    }

    public static void dfs(boolean[] visited, int now, int sum, int depth) {
        if (depth == N - 1) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 0; i < N; i++) {

            if (visited[i]) continue;

            visited[i] = true;
            dfs(visited, i, sum + map[now][i], depth + 1);
            visited[i] = false;

        }
    }
}