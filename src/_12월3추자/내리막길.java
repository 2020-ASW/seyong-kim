package _12월3추자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 내리막길 {
    static int N, M;
    static int[][] map;
    static int[][] dp;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());   // 세로
        N = Integer.parseInt(st.nextToken());   // 가로

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        int ans = dfs(new Spot(0, 0, map[0][0]));

        System.out.println(ans);
    }


    private static int dfs(Spot spot) {
        // 도착 지점에 도달한 경우
        if (spot.x == M - 1 && spot.y == N - 1) {
            return 1;
        }

        // 방문한 적이 없어 탐색을 안한 경우
        if (dp[spot.x][spot.y] == -1) {

            // 방문 체크
            dp[spot.x][spot.y] = 0;

            // 사방 탐색
            for (int[] dir : directions) {
                int nx = spot.x + dir[0];
                int ny = spot.y + dir[1];

                // map 을 벗어난 경우
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
                    continue;
                }

                // 지금 위치보다 높은 경우
                if (spot.h <= map[nx][ny]) {
                    continue;
                }

                dp[spot.x][spot.y] += dfs(new Spot(nx, ny, map[nx][ny]));
            }
        }

        return dp[spot.x][spot.y];
    }

    static class Spot {
        int x;
        int y;
        int h;

        Spot(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
}
