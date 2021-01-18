package _1월4주차;

import java.io.*;
import java.util.*;

public class ACMCraft {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            ArrayList<Integer>[] tech = new ArrayList[N + 1];
            for (int i = 1; i < tech.length; i++) tech[i] = new ArrayList<>();


            st = new StringTokenizer(br.readLine());

            int[] time = new int[N + 1];
            for (int i = 1; i <= N; i++) time[i] = Integer.parseInt(st.nextToken());

            int[] indegree = new int[N + 1];
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                tech[u].add(v);
                indegree[v]++;
            }
            int W = Integer.parseInt(br.readLine());

            System.out.println(topologicalSort(tech, indegree, time, W));
        }
    }

    private static int topologicalSort(ArrayList<Integer>[] tech, int[] indegree, int[] time, int W) {
        // {pre, now}
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(new int[]{i, i});
        }
        int[] spendTime = new int[time.length];

        while (!queue.isEmpty()) {
            int[] vertex = queue.poll();
            int pre = vertex[0];
            int now = vertex[1];

            if (pre == now) spendTime[now] = time[now];
            else spendTime[now] = Math.max(spendTime[now], spendTime[pre] + time[now]);

            for (int next : tech[now]) {
                indegree[next]--;

                if (indegree[next] == 0)
                    queue.offer(new int[]{now, next});
                else{
                    spendTime[next] = Math.max(spendTime[next], spendTime[now] + time[next]);
                }
            }
        }
        return spendTime[W];
    }
}
