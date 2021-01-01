package _1월1주차;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 줄기세포배양 {
    static final int ACTIVE = 1, INACTIVE = 0, DEATH = -1;
    static int N, M, K;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Cell> queue;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int TC = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= TC; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new int[N + K][M + K];
            visited = new boolean[N + K][M + K];
            queue = new LinkedList<>();

            for (int i = (K + 1) / 2; i < N + (K + 1) / 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = (K + 1) / 2; j < M + (K + 1) / 2; j++) {
                    int val = Integer.parseInt(st.nextToken());

                    if (val != 0) {
                        queue.add(new Cell(i, j, val));
                        visited[i][j] = true;
                    }
                    board[i][j] = val;
                }
            }
            bw.write("#" + testCase + " " + propagate() + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static int propagate() {
        while (K-- > 0) {
            int len = queue.size();
            for (Cell cell : queue) {
                if (cell.status == ACTIVE)
                    setValue(cell);
            }

            for (int i = 0; i < len; i++) { // while (!queue.isEmpty()) 하면 무한 루프, 새로 추가된 애들 제외하고 for loop
                Cell cell = queue.poll();

                if (cell.status == ACTIVE) {
                    for (int[] dir : direction) {
                        int nx = cell.x + dir[0];
                        int ny = cell.y + dir[1];

                        if (visited[nx][ny]) continue;

                        queue.add(new Cell(nx, ny, board[nx][ny]));
                        visited[nx][ny] = true;
                    }
                }
                cell.next();

                if (cell.status == DEATH) continue;

                queue.add(cell);
            }
        }
        return queue.size();
    }

    private static void setValue(Cell cell) {
        for (int[] dir : direction) {
            int nx = cell.x + dir[0];
            int ny = cell.y + dir[1];

            if (visited[nx][ny]) continue;

            board[nx][ny] = Math.max(board[nx][ny], cell.life);
        }

    }

    static class Cell {
        int x, y, life;
        int status;
        int curLife;

        Cell(int x, int y, int life) {
            this.x = x;
            this.y = y;
            this.life = life;
            curLife = life;
            this.status = INACTIVE;
        }

        public void next() {
            switch (status) {
                case INACTIVE:
                    curLife--;
                    if (curLife == 0)
                        status = ACTIVE;
                    break;
                case ACTIVE:
                    curLife++;
                    if (curLife == life)
                        status = DEATH;
                    break;
            }
        }

        @Override
        public String toString() {
            String str = "";
            switch (status) {
                case INACTIVE:
                    str = "I";
                    break;
                case ACTIVE:
                    str = "A";
                    break;
                case DEATH:
                    str = "D";
                    break;
            }
            return "[" + str + "]" + life;
        }
    }

    private static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
