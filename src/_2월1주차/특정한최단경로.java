package _2월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, d));
            graph[v].add(new Node(u, d));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());


        int[] fromOneTo = dijkstra(graph, 1);
        int fromV1ToV2 = dijkstra(graph, v1)[v2];
        int[] fromNTo = dijkstra(graph, N);

        int answer = Math.min(fromOneTo[v1] + fromV1ToV2 + fromNTo[v2], fromOneTo[v2] + fromV1ToV2 + fromNTo[v1]);

        if (answer >= 9999) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    private static int[] dijkstra(ArrayList<Node>[] graph, int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 9999); // 1 <= dist <= 1000
        pq.add(new Node(src, 0));
        dist[src] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.dist > dist[now.no]) continue;

            for (Node next : graph[now.no]) {
                if (dist[next.no] >= dist[now.no] + next.dist) {
                    dist[next.no] = dist[now.no] + next.dist;
                    pq.add(new Node(next.no, dist[next.no]));
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node> {
        int no, dist;

        Node(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
