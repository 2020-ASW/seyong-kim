package _12월3주차;

import java.util.*;

class 트리트리오중간값 {

    static ArrayList<Integer>[] nodes;
    static int N;

    public static int solution(int n, int[][] edges) {
        N = n;
        nodes = new ArrayList[N + 1];

        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new ArrayList<Integer>();
        }

        for (int[] edge : edges) {
            nodes[edge[0]].add(edge[1]);
            nodes[edge[1]].add(edge[0]);
        }

        // 임의의 정점 1에서 각 정점(들) X 구하기
        int start = 1;
        int[] distanceArr = bfs(start);

        // X는 임의의 정점 1에서 가장 먼 노드
        int X = start;
        for (int i = 1; i < distanceArr.length; i++) {
            if (distanceArr[X] <= distanceArr[i]) {
                X = i;
            }
        }


        // X로부터 가장 멀리 있는 Y 정점 구하기
        distanceArr = bfs(X);
        int Y = X;
        for (int i = 1; i < distanceArr.length; i++) {
            if (distanceArr[Y] <= distanceArr[i]) {
                Y = i;
            }
        }
        // Y의 정점 개수 카운트
        int cnt = 0;
        for (int i = 1; i < distanceArr.length; i++) {
            if (distanceArr[Y] == distanceArr[i]) {
                cnt++;
            }
        }
        // 만약 Y 정점의 개수가 2개 이상이면 트리 지름이 정답
        if (cnt >= 2) return distanceArr[Y];


        // Y로 부터 가장 멀리있는 Z 정점 구하기
        distanceArr = bfs(Y);
        int Z = Y;
        for (int i = 0; i < distanceArr.length; i++) {
            if (distanceArr[Z] <= distanceArr[i]) {
                Z = i;
            }
        }
        // Z의 정점 개수 카운트
        cnt = 0;
        for (int i = 1; i < distanceArr.length; i++) {
            if (distanceArr[Z] == distanceArr[i]) {
                cnt++;
            }
        }

        if (cnt >= 2)   // Z가 2개 이상이면 트리 지름이 정답
            return distanceArr[Z];
        else            // Z가 1개면 트리 지름 -1이 정답
            return distanceArr[Z] - 1;

    }

    // 최대 거리의 있는 정점(들) 구하기
    private static int[] bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(node);
        visited[node] = true;
        int[] dist = new int[N + 1];

        while (!queue.isEmpty()) {
            int curNode = queue.poll();

            for (int linkedNode : nodes[curNode]) {
                if (visited[linkedNode]) continue;

                visited[linkedNode] = true;
                queue.add(linkedNode);
                dist[linkedNode] = dist[curNode] + 1;
            }

        }
        return dist;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        System.out.println(solution(5, new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 5}}));
        System.out.println(solution(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {4, 5}}));
    }
}