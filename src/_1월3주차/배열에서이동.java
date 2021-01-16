package _1월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 배열에서이동 {
    static int N, answer;
    static int[][] map;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        int min = 201, max = 0;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        answer = 201;
        int l = 0, r = max - min;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (bfs(mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean bfs(int target) {
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        queue.add(new Position(1, 1, 201, 0));
        visited[1][1] = true;

        int result = 201;

        while (!queue.isEmpty()) {
            Position now = queue.poll();

            if (now.x == N && now.y == N) {
                System.out.println("max:" + now.max + " min:" + now.min + " diff:" + (now.max - now.min));
                result = Math.min(result, now.max - now.min);

            }

            for (int[] dir : direction) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 1 || ny < 1 || nx > N || ny > N || visited[nx][ny]) continue;

//                if(now.min > target || now.max < target) continue;

                visited[nx][ny] = true;

                queue.add(new Position(nx, ny,
                        Math.min(now.min, map[nx][ny]),
                        Math.max(now.max, map[nx][ny])
                ));
            }
        }
        System.out.println("target = " + target + "  result = " + result);
        return target < result;
    }

    static class Position {
        int x, y, min, max;

        Position(int x, int y, int min, int max) {
            this.x = x;
            this.y = y;
            this.min = min;
            this.max = max;
        }
    }
}
