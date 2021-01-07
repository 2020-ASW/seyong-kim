package _1월2주차;

import java.util.*;

public class NumberOfIslands {
    static int[][] map;
    static int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int N, M;

    public static int numIslands(char[][] grid) {
        map = new int[grid.length][grid[0].length];
        N = grid.length;
        M = grid[0].length;
        int sector = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && grid[i][j] == '1') {
                    bfs(grid, i, j, sector);
                    sector++;
                }
            }
        }
        return sector - 1;
    }

    private static void bfs(char[][] grid, int x, int y, int sector) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        map[x][y] = sector;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            for (int[] dir : direction) {
                int nx = pos[0] + dir[0];
                int ny = pos[1] + dir[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (map[nx][ny] != 0) continue;

                if (grid[nx][ny] == '1') {
                    map[nx][ny] = sector;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

    }
}