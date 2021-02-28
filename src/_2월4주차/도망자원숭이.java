package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 도망자원숭이 {
    static int N, M, Q;
    static int[][] map, cost;
    static int[] dogs;
    static Integer[] dogsIdx;
    static final int INF = 99999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        cost = new int[N + 1][N + 1];
        dogs = new int[N + 1];
        dogsIdx = new Integer[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i], INF);
            Arrays.fill(cost[i], INF);

            dogs[i] = Integer.parseInt(st.nextToken());
            dogsIdx[i] = i;

            map[i][i] = 0;
            cost[i][i] = dogs[i];
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            map[u][v] = map[v][u] = t;
            cost[u][v] = cost[v][u] = t + Math.max(dogs[u], dogs[v]);
        }

        sortArray();

        for (int k = 1; k <= N; k++) {
            int idx = dogsIdx[k];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][idx] == INF || map[idx][i] == INF) continue;

                    if (map[i][j] > map[i][idx] + map[idx][j]) {
                        map[i][j] = map[i][idx] + map[idx][j];
                    }
                    if (cost[i][j] > map[i][j] + Math.max(dogs[i], Math.max(dogs[idx], dogs[j]))) {
                        cost[i][j] = map[i][j] + Math.max(dogs[i], Math.max(dogs[idx], dogs[j]));
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            String res = (cost[u][v] != INF) ? String.valueOf(cost[u][v]) : "-1";
            sb.append(res).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void sortArray() {
        Arrays.sort(dogsIdx, 1, N + 1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dogs[o1] - dogs[o2];
            }
        });
    }

    private static void print(int[][] map) {
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[i].length; j++) {
                String s = (map[i][j] != INF) ? String.valueOf(map[i][j]) : "INF";
                System.out.print(s + "\t");
            }
            System.out.println();
        }
    }
}
