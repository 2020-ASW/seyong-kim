package PROGRAMMERS;

import java.util.*;

/**
 * 1. 임의의 한 정점(1)으로부터 가장 거리가 먼 정점(X)을 구한다
 * 2. X 로부터 가장 거리가 먼 정점(Y)를 구한다
 * 2-1) X의 개수가 여러 개인 경우 = 트리의 지름이 정답이 된다
 * 2-2) X의 개수가 하나(D)인 경우
 */

class 트리트리오중간값 {

    static int[][] tree;

    public static int solution(int n, int[][] edges) {
        tree = new int[n + 1][n + 1];

        for (int[] edge : edges) {
            tree[edge[0]][edge[1]] = 1;
            tree[edge[1]][edge[0]] = 1;
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
        boolean[] visited = new boolean[tree.length];

        queue.add(node);
        visited[node] = true;
        int[] dist = new int[tree.length];

        while (!queue.isEmpty()) {
            int curNode = queue.poll();

            for (int i = 1; i < tree.length; i++) {

                if (tree[curNode][i] == 0 || visited[i]) continue;

                visited[i] = true;
                queue.add(i);
                dist[i] = dist[curNode] + 1;
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}});
        solution(5, new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 5}});
        solution(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {4, 5}});
    }
}















