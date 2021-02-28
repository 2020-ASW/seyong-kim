package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 행렬만들기_네트워크플로우 {
    static short[][] capacity;
    static short[][] flowArr;
    static ArrayList<Integer>[] graph;
    static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N * N + 3];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

        capacity = new short[N * N + 3][N * N + 3];
        flowArr = new short[N * N + 3][N * N + 3];

        int S = 0;
        int E = 2 * N + 1;
        int rowSum = 0, colSum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            capacity[S][i] = Short.parseShort(st.nextToken());
            rowSum += capacity[S][i];
            graph[S].add(i);
            graph[i].add(S);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = N + 1; i <= 2 * N; i++) {
            capacity[i][E] = Short.parseShort(st.nextToken());
            colSum += capacity[i][E];
            graph[i].add(E);
            graph[E].add(i);
        }

        if (rowSum != colSum) {
            System.out.println(-1);
            return;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = N + 1; j <= 2 * N; j++) {
                graph[i].add(j);
                graph[j].add(i);
                capacity[i][j] = 1;
            }
        }
        total = 0;
        maxFlow(S, E);

        if (total != rowSum) {
            System.out.println(-1);
            return;
        }

        System.out.println(1);
        for (int i = 1; i <= N; i++) {
            for (int j = N + 1; j <= 2 * N; j++) {
                System.out.print(flowArr[i][j]);
            }
            System.out.println();
        }
    }

    private static void maxFlow(int start, int end) {
        while (true) {
            int[] pre = new int[end + 1];
            Arrays.fill(pre, -1);
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);

            while (!queue.isEmpty() && pre[end] == -1) {
                int now = queue.poll();

                for (int next : graph[now]) {
                    if (capacity[now][next] > flowArr[now][next] && pre[next] == -1) {
                        queue.offer(next);
                        pre[next] = now;
                    }
                }
            }
            if (pre[end] == -1) break;

            int flow = 1;
            for (int i = end; i != start; i = pre[i]) {
                flowArr[pre[i]][i] += flow;
                flowArr[i][pre[i]] -= flow;
            }
            total += flow;
        }
    }
}
