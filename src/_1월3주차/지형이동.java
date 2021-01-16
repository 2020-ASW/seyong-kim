package _1월3주차;

import java.util.*;

class 지형이동 {
    static int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[] parent;

    public static int solution(int[][] land, int height) {

        // Grouping
        int[][] group = new int[land.length][land.length];
        int sector = 1;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land.length; j++) {
                if (group[i][j] == 0) {
                    grouping(land, group, height, sector, new Position(i, j));
                    sector++;
                }
            }
        }

        // Find boundary
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land.length; j++) {
                for (int[] dir : direction) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];

                    if (nx < 0 || ny < 0 || nx >= group.length || ny >= group.length) continue;
                    if (group[i][j] == group[nx][ny]) continue;

                    int u = group[i][j];
                    int v = group[nx][ny];
                    int diff = Math.abs(land[i][j] - land[nx][ny]);

                    list.add(new Node(u, v, diff));
                }
            }
        }
        Collections.sort(list);
        System.out.println(list.toString());
        int answer = 0, cnt = 0;

        parent = new int[sector];
        for (int i = 1; i < parent.length; i++) parent[i] = i;
        for (Node node : list) {
            if (find(node.now) != find(node.next)) {
                union(node.now, node.next);
                answer += node.diff;
                cnt++;
                if (cnt == sector - 2) break;
            }
        }

        return answer;
    }

    private static void grouping(int[][] land, int[][] group, int height, int sector, Position start) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(start);
        group[start.x][start.y] = sector;

        while (!queue.isEmpty()) {
            Position now = queue.poll();

            for (int[] dir : direction) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || ny < 0 || nx >= group.length || ny >= group.length) continue;
                if (group[nx][ny] != 0) continue;

                int diff = Math.abs(land[nx][ny] - land[now.x][now.y]);
                if (height < diff) continue;

                group[nx][ny] = sector;
                queue.add(new Position(nx, ny));
            }
        }
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;
        parent[y] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }


    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        int now;
        int next;
        int diff;

        Node(int now, int next, int diff) {
            this.now = now;
            this.next = next;
            this.diff = diff;
        }

        @Override
        public int compareTo(Node o) {
            if (this.diff - o.diff != 0) return this.diff - o.diff;
            else {
                if (this.now - o.now < 0) return -1;
                else return 1;
            }
        }

        @Override
        public String toString() {
            return now + "->" + next + "(" + diff + ") ";
        }
    }

    public static void main(String[] args) {
        int[][] land = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
        int height = 3;
        System.out.println(solution(land, height));

        land = new int[][]{{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}};
        height = 1;
        System.out.println(solution(land, height));
    }
}
