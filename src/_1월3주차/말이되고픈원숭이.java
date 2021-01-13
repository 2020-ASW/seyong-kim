package _1월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 말이되고픈원숭이 {
    static int K, W, H, answer;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};  // 말처럼 이동할 때

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[W][H];
        visited = new boolean[W][H][K + 1];

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < H; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;
        bfs(new Monkey(0, 0, 0, 0));

        boolean arrived = false;
        for (int k = 0; k <= K; k++) {
            if (visited[W - 1][H - 1][k]) {
                arrived = true;
                break;
            }
            arrived = visited[W - 1][H - 1][k];
        }

        System.out.println(arrived ? answer : -1);
    }

    private static void bfs(Monkey start) {
        Queue<Monkey> queue = new LinkedList<>();
        visited[start.x][start.y][start.jump] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Monkey now = queue.poll();

            if (now.x == W - 1 && now.y == H - 1) {
                answer = Math.min(answer, now.action);
            }

            int len = now.jump < K ? 12 : 4;
            for (int i = 0; i < len; i++) {
                int nx = now.x + dir[i][0];
                int ny = now.y + dir[i][1];
                int nJump = i < 4 ? now.jump : now.jump + 1;
                int nAct = now.action + 1;

                if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny][nJump]) continue;

                visited[nx][ny][nJump] = true;

                queue.add(new Monkey(nx, ny, nAct, nJump));
            }
        }
    }

    static class Monkey {
        int x, y, jump;
        int action;

        Monkey(int x, int y, int action, int jump) {
            this.x = x;
            this.y = y;
            this.action = action;
            this.jump = jump;
        }
    }
}
