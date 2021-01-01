package _1월1주차;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    static List<List<String>> result;

    public static List<List<String>> solveNQueens(int n) {
        int[] board = new int[n + 1];
        result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            board[1] = i;

            dfs(board, 1, n);
        }

        return result;
    }

    private static void dfs(int[] board, int row, int n) {
        if (row == n) {
            List<String> line = new ArrayList<>();

            for (int i = 1; i < board.length; i++) {
                StringBuilder sb = new StringBuilder();

                for (int j = 1; j < board.length; j++) {
                    if (board[i] == j) {
                        sb.append("Q");
                        continue;
                    }
                    sb.append(".");
                }

                line.add(sb.toString());
            }

            result.add(line);

            return;
        }

        for (int i = 1; i <= n; i++) {
            board[row + 1] = i;

            if (isPossible(board, row + 1)) {
                dfs(board, row + 1, n);
                continue;
            }
            board[row + 1] = 0;
        }
    }

    private static boolean isPossible(int[] board, int row) {
        for (int i = 1; i < row; i++) {
            if (board[i] == board[row])
                return false;
            if (Math.abs(i - row) == Math.abs(board[i] - board[row]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4).toString());
    }
}
