package _1월2주차;

import java.io.*;
import java.util.*;

public class 등산로조성 {
    static int N, K, answer;
    static int[][] map, direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    static List<Road> top;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= TC; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];
            int highest = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    highest = Math.max(highest, map[i][j]);
                }
            }

            top = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == highest) {
                        top.add(new Road(i, j));
                    }
                }
            }
            answer = 0;
            for (Road topPosition : top) {
                visited[topPosition.x][topPosition.y] = true;
                dfs(topPosition);
                visited[topPosition.x][topPosition.y] = false;
            }

            bw.write("#" + testCase + " " + answer + "\n");
        }
        bw.flush();
    }

    private static void dfs(Road pos) {
        for (int[] dir : direction) {
            int nx = pos.x + dir[0];
            int ny = pos.y + dir[1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

            int diff = map[pos.x][pos.y] - map[nx][ny];

            if (diff > 0) {                 // 정상적인 경우
                pos.go(nx, ny);
                visited[nx][ny] = true;

                dfs(pos);

                pos.back(nx - dir[0], ny - dir[1]);
                visited[nx][ny] = false;

            } else if (-1 * diff < K) {     // 깍아서 가보는 경우
                if (!pos.dig) {
                    pos.dig = true;
                    map[nx][ny] -= K;
                    pos.go(nx, ny);
                    visited[nx][ny] = true;

                    dfs(pos);

                    pos.back(nx - dir[0], ny - dir[1]);
                    visited[nx][ny] = false;
                    pos.dig = false;
                    map[nx][ny] += K;
                }
            }
        }
        answer = Math.max(answer, pos.distance);
    }

    static class Road {
        int x, y;
        int distance;
        boolean dig = false;

        Road(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = 1;
        }

        public void go(int nx, int ny) {
            this.x = nx;
            this.y = ny;
            this.distance += 1;
        }

        public void back(int bx, int by) {
            this.x = bx;
            this.y = by;
            this.distance -= 1;
        }
    }
}
