package _1월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 합리적인이동경로 {
    static ArrayList<Node>[] graph;
    static int[] minCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수

        init(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());   // src
            int b = Integer.parseInt(st.nextToken());   // dst
            int dist = Integer.parseInt(st.nextToken());   // distance

            graph[a].add(new Node(b, dist));
            graph[b].add(new Node(a, dist));
        }

        dijkstra(2);
        cnt = new int[N + 1];
        Arrays.fill(cnt, -1);

        int answer = countPath(new Node(1, 0));

        System.out.println(answer);
    }


    static int[] cnt;

    private static int countPath(Node now) {
        // now 에서 2로 이동하는것은 항상 합리적인 경로
        if (now.idx == 2) return 1;

        // memoization
        if (cnt[now.idx] != -1) return cnt[now.idx];

        cnt[now.idx] = 0;

        for (Node next : graph[now.idx]) {
            // now 와 연결된 노드들 중에서
            // (now ~ T 까지의 최단 거리) > (next ~ T 까지의 최단 거리) = 합리적인 경로
            if (minCost[now.idx] > minCost[next.idx]) {
                cnt[now.idx] += countPath(next);
            }
        }

        return cnt[now.idx];
    }

    private static void init(int n) {
        graph = new ArrayList[n + 1];
        Arrays.fill(graph, new ArrayList<>());
        minCost = new int[n + 1];
        Arrays.fill(minCost, Integer.MAX_VALUE);
    }

    private static void dijkstra(int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        minCost[src] = 0;
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cost > minCost[now.idx]) continue;

            for (Node next : graph[now.idx]) {
                if (minCost[next.idx] > minCost[now.idx] + next.cost) {
                    minCost[next.idx] = minCost[now.idx] + next.cost;
                    pq.add(new Node(next.idx, minCost[next.idx]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
