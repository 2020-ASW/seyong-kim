package _1월2주차;

import java.util.Arrays;

// Fail 78.3/100 (not passed  5 test case)

public class 경주로건설 {
    static int N, answer;
    static int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static boolean[][] visited;
    static int[][] dp;

    public static int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        N = board.length;
        visited = new boolean[N][N];
        dfs(board, 0, 0, 0, 0);

        for (int[] row : dp){
            System.out.println(Arrays.toString(row));
        }

        return answer;
    }

    private static void dfs(int[][] board, int x, int y, int cost, int preDir) {
        if (x == N - 1 && y == N - 1) {
            answer = Math.min(answer, cost);
            return;
        }
        // 현재 cost가 answer보다 커지면 볼필요 없음
        if(cost >= answer) return;

        for (int d = 1; d <= direction.length; d++) {
            int nx = x + direction[d - 1][0];
            int ny = y + direction[d - 1][1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

            if (board[nx][ny] == 1) continue;

            visited[nx][ny] = true;
            int add = 100;

            if (preDir != 0 && preDir != d) {
                add += 500;
            }
            dfs(board, nx, ny, cost + add, d);

            visited[nx][ny] = false;
        }
    }
}
