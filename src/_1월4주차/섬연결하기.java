package _1월4주차;

import java.util.*;

public class 섬연결하기 {
    public int solution(int n, int[][] costs) {
        ArrayList<Island>[] map = new ArrayList[n];
        for (int i = 0; i < map.length; i++) map[i] = new ArrayList<>();

        for (int[] cost : costs) {
            map[cost[0]].add(new Island(cost[1], cost[2]));
            map[cost[1]].add(new Island(cost[0], cost[2]));
        }
        boolean[] visited = new boolean[map.length];
        int[] dist = dijkstra(map, visited, costs[0][0]);

        int answer = 0;
        for (int minCost : dist)
            answer += minCost;

        return answer;
    }

    private static int[] dijkstra(ArrayList<Island>[] map, boolean[] visited, int src) {
        PriorityQueue<Island> pq = new PriorityQueue<>();
        int[] dist = new int[map.length];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Island(src, 0));
        dist[src] = 0;

        while (!pq.isEmpty()) {
            Island now = pq.poll();

            visited[now.no] = true;

            for (Island next : map[now.no]) {
                // 최단 거리의 경로만 체크해준다
                // 이미 최단거리를 찾아놨기 때문에 방문했던 노드는 방문하지 않는다
                if (!visited[next.no] && dist[next.no] > next.cost) {
                    dist[next.no] = next.cost;
                    pq.add(new Island(next.no, dist[next.no]));
                }
            }
        }
        return dist;
    }

    static class Island implements Comparable<Island> {
        int no, cost;

        Island(int no, int cost) {
            this.no = no;
            this.cost = cost;
        }

        @Override
        public int compareTo(Island o) {
            return this.cost - o.cost;
        }
    }
}
