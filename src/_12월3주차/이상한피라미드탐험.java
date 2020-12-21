package _12월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이상한피라미드탐험 {

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TestCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TestCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int N = getMapSize(Math.max(a, b));
            int[][] map = new int[N][N];
            visited = new boolean[N][N];
            Position[] pos = buildMap(map, a, b);

            int ans = getDistance(map, pos[0], pos[1]);

            System.out.println("#" + tc + " " + ans);
        }

    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int getMapSize(int x) {
        int line = 1;
        while (true) {
            int cnt = line * (line + 1) / 2;
            if (x < cnt) {
                break;
            }
            line++;
        }
        return line;
    }

    private static Position[] buildMap(int[][] map, int a, int b) {
        Position[] pos = new Position[2];
        int val = 1;
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], -1);
            for (int j = 0; j <= i; j++) {
                map[i][j] = val;

                if (val == a)
                    pos[0] = new Position(i, j, 0);

                if (val == b)
                    pos[1] = new Position(i, j, 0);
                val++;
            }
        }
        return pos;
    }

    // 왼쪽 위, 오른쪽 위, 왼쪽, 오른쪽, 왼쪽 아래, 오른쪽 아래
    static int[][] directions = {{-1, -1}, {-1, 0}, {0, -1}, {0, 1}, {1, 0}, {1, 1}};

    private static int getDistance(int[][] map, Position start, Position end) {
        Queue<Position> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;

        int distance = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Position p = q.poll();

            if (p.x == end.x && p.y == end.y) {
                distance = Math.min(distance, p.distance);
            }

            for (int[] dir : directions) {
                int nx = p.x + dir[0];
                int ny = p.y + dir[1];

                if (nx < 0 || ny < 0 || nx >= map.length || ny >= map.length || map[nx][ny] == -1 || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new Position(nx, ny, p.distance + 1));

            }
        }
        return distance;
    }

    static class Position {
        int x, y, distance;

        Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}