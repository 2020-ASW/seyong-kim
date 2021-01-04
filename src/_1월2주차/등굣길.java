package _1월2주차;

import java.util.Arrays;

public class 등굣길 {
    static int[][] map;
    static int M, N;
    static final int PUDDLES = -2;
    static int[][] direction = {{1, 0}, {0, 1}};

    public static int solution(int m, int n, int[][] puddles) {
        M = m;
        N = n;
        map = new int[m + 1][n + 1];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], -1);
        }
        for (int[] pos : puddles) {
            map[pos[0]][pos[1]] = PUDDLES;
        }

        int answer = dfs(1, 1);
        return answer % 1000000007;
    }

    private static int dfs(int x, int y) {
        if (x == M && y == N) {
            return 1;
        }
        if (map[x][y] == -1) {
            map[x][y] = 0;

            for (int[] dir : direction) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 1 || ny < 1 || nx > M || ny > N) continue;

                if (map[nx][ny] == PUDDLES) continue;

                map[x][y] += dfs(nx, ny) % 1000000007;
            }
        }
        return map[x][y];
    }
}
