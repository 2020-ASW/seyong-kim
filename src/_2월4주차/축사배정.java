package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 축사배정 {
    static int N, M;
    static int[][] capacity;
    static int[][] flow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        capacity = new int[N + M + 2][N + M + 2];
        flow = new int[N + M + 2][N + M + 2];

        int source = 0, sink = N + M + 1;
        for (int i = 1; i <= N; i++) {
            capacity[source][i] = 1;
        }
        for (int i = N + 1; i <= N + M; i++) {
            capacity[i][sink] = 1;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cage = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cage; j++) {
                int idx = Integer.parseInt(st.nextToken()) + N;
                capacity[i][idx] = 1;
            }
        }

        System.out.println(maxFlow(source, sink));
    }

    private static int maxFlow(int source, int sink) {
        int totalFlow = 0;

        while (true) {
            int[] prev = new int[N + M + 2];
            Arrays.fill(prev, -1);
            Queue<Integer> queue = new LinkedList<>();
            prev[source] = source;
            queue.add(source);

            while (!queue.isEmpty() && prev[sink] == -1) {
                int now = queue.poll();

                for (int next = 0; next < N + M + 2; next++) {
                    if (capacity[now][next] > flow[now][next] && prev[next] == -1) {
                        queue.add(next);
                        prev[next] = now;
                    }
                }
            }
            if (prev[sink] == -1) break;

            int amount = Integer.MAX_VALUE;
            for (int i = sink; i != source; i = prev[i]) {
                amount = Math.min(amount, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for (int i = sink; i != source; i = prev[i]) {
                flow[prev[i]][i] += amount;
                flow[i][prev[i]] -= amount;
            }
            totalFlow += amount;
        }
        return totalFlow;
    }
}
