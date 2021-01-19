package _1월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파티 {
    static int N, M, X;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] map = new ArrayList[N + 1];
        ArrayList<Node>[] reverseMap = new ArrayList[N + 1];

        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<>();
            reverseMap[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            map[u].add(new Node(v, t));
            reverseMap[v].add(new Node(u, t));
        }

        // 파티 장소에서부터 각 정점까지의 최단 거리
        int[] partyPath = dijkstra(map, X);

        // 각 정점에서 파티 장소로 향하는 최단 거리
        int[] homePath = dijkstra(reverseMap, X);

        // System.out.println(Arrays.toString(partyPath));
        // System.out.println(Arrays.toString(homePath));

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, partyPath[i] + homePath[i]);
        }


        System.out.println(answer);
    }

    private static int[] dijkstra(ArrayList<Node>[] map, int party) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(party, 0));

        int[] dist = new int[map.length];
        Arrays.fill(dist, INF);

        dist[party] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.time > dist[now.no]) continue;

            for (Node next : map[now.no]) {
                if (dist[next.no] > dist[now.no] + next.time) {
                    dist[next.no] = dist[now.no] + next.time;
                    pq.add(new Node(next.no, dist[next.no]));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int no, time;

        Node(int no, int time) {
            this.no = no;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
