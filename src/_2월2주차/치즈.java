package _2월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈 {
    static int N, M, totalCheese;
    static int[][] map;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int OUTSIDE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        totalCheese = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) totalCheese++;
            }
        }

        checkOutsideZone();
        print(map);

        int time = 0;
        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        if (isMelt(new Position(i, j))) {
                            map[i][j] = 3;
                            totalCheese--;
                        }
                    }
                }
            }

            print(map);
            checkOutsideZone();

            time++;

            if (totalCheese == 0) break;
        }
        System.out.println(time);
    }

    private static boolean isMelt(Position pos) {
        int val = 0;
        for (int[] dir : direction) {
            int nx = pos.x + dir[0];
            int ny = pos.y + dir[1];

            if (map[nx][ny] == OUTSIDE) val++;
        }

        return val >= 2;
    }


    private static void checkOutsideZone() {
        Queue<Position> queue = new LinkedList<>();
        boolean[][] checked = new boolean[N][M];

        queue.add(new Position(0, 0));
        checked[0][0] = true;
        map[0][0] = OUTSIDE;

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            for (int[] dir : direction) {
                int nx = pos.x + dir[0];
                int ny = pos.y + dir[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (checked[nx][ny]) continue;
                if (map[nx][ny] == 1) continue;
                if (map[nx][ny] == 3) map[nx][ny] = -1;

                map[nx][ny] = OUTSIDE;
                checked[nx][ny] = true;
                queue.add(new Position(nx, ny));
            }
        }
    }

    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    private static void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 2)
                    System.out.print("  ");
                else
                    System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
