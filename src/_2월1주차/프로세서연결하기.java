package _2월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 프로세서연결하기 {
    static int N, maxCore, answer;
    static int[][] board;
    static List<int[]> processors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= TC; testCase++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];

            processors = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; st.hasMoreTokens(); j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        if (i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;
                        processors.add(new int[]{i, j});
                    }
                }
            }

            maxCore = 0;
            answer = Integer.MAX_VALUE;
            dfs(0, 0, 0, processors);

            sb.append("#").append(testCase).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


    private static void dfs(int idx, int coreCnt, int lines, List<int[]> processors) {
        if (idx == processors.size()) {
            if (maxCore < coreCnt) {
                maxCore = coreCnt;
                answer = lines;
            } else if (maxCore == coreCnt) {
                answer = Math.min(answer, lines);
            }
            return;
        }

        for (int[] dir : direction) {
            if (!isConnected(processors.get(idx), dir)) continue;

            int len = putLines(processors.get(idx), dir, 2);
            dfs(idx + 1, coreCnt + 1, lines + len, processors);
            putLines(processors.get(idx), dir, 0);
        }
        dfs(idx + 1, coreCnt, lines, processors);
    }

    private static int putLines(int[] core, int[] dir, int val) {
        int len = 0;
        int nx = core[0] + dir[0];
        int ny = core[1] + dir[1];
        while (nx >= 0 && ny >= 0 && nx < N && ny < N) {
            board[nx][ny] = val;
            nx += dir[0];
            ny += dir[1];
            len++;
        }
        return len;
    }

    private static boolean isConnected(int[] core, int[] dir) {
        int nx = core[0] + dir[0];
        int ny = core[1] + dir[1];
        while (nx >= 0 && ny >= 0 && nx < N && ny < N) {
            if (board[nx][ny] != 0) return false;
            nx += dir[0];
            ny += dir[1];
        }
        return true;
    }


}
