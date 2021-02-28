package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 도망자원숭이 {
    static int N, M, Q;
    static int[][] map;
    static int[] threat;
    static final int INF = 99999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }
        threat = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            threat[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            map[u][v] = t;
            map[v][u] = t;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    int threatTime = (k == j) ? 0 : threat[k];
                    if (map[i][j] + threatTime > map[i][k] + map[k][j] + threatTime) {
                        map[i][j] = map[i][k] + map[k][j] + threatTime;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            String res = (map[u][v] != INF) ? String.valueOf(map[u][v]) : "-1";
            sb.append(res).append("\n");
        }
        System.out.println(sb.toString());

    }
}
