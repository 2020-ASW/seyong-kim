package _1월2주차;

import java.util.*;

// (1) 실패 78.3/100 (not passed 5 test case)
// (2) dfs에서 bfs로 변경
// (3) 이미 설계된 곳이라면 값 비교 후, 최소 비용 저장

public class 경주로건설 {
    static int N, answer;
    static int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        N = board.length;

        bfs(board, new Car(0, 0, 0, -1));

        return answer;
    }

    private static void bfs(int[][] board, Car start) {
        Queue<Car> queue = new LinkedList<>();
        board[start.x][start.y] = 1;
        queue.add(start);

        while (!queue.isEmpty()) {
            Car cur = queue.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                answer = Math.min(answer, cur.cost);
                continue;
            }

            for (int d = 0; d < direction.length; d++) {
                int nx = cur.x + direction[d][0];
                int ny = cur.y + direction[d][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 1) continue;

                int newCost = cur.cost;

                if (cur.preDir == -1 || cur.preDir == d) {
                    newCost += 100;
                } else { // 다른 방향으로 갈 경우(회전하는 경우만)
                    newCost += 100 + 500;
                }

                if (board[nx][ny] == 0) {
                    board[nx][ny] = newCost;
                    queue.add(new Car(nx, ny, newCost, d));
                } else if (board[nx][ny] >= newCost) {
                    board[nx][ny] = Math.min(board[nx][ny], newCost);
                    queue.add(new Car(nx, ny, newCost, d));
                }

            }
        }
    }

    static class Car {
        int x, y, cost, preDir;

        Car(int x, int y, int cost, int preDir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.preDir = preDir;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]" + cost + "/" + preDir;
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[][]
                {
                        {0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 1, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                };
        System.out.println(solution(board));
    }
}