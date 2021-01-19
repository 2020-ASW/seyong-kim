package _1월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 낚시왕 {
    static int R, C, M;
    static int[][] map;
    static Shark[] sharks;
    static int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R + 1][1 + C + 1];
        sharks = new Shark[M + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); // x
            int c = Integer.parseInt(st.nextToken()); // y
            int s = Integer.parseInt(st.nextToken()); // speed
            int d = Integer.parseInt(st.nextToken()); // direction
            int z = Integer.parseInt(st.nextToken()); // size
            map[r][c] = i;
            sharks[i] = new Shark(r, c, s, d, z);
        }

        Fisher fisher = new Fisher(0, 0);
        for (int i = 1; i <= C; i++) {
            fisher.y = i;
            fisher.fish(i);

            map = new int[R + 1][1 + C + 1];

            moveShark();
        }

        System.out.println(fisher.sharkSize);
    }

    private static void moveShark() {
        int[] changeDir = {0, 2, 1, 4, 3};

        for (int i = 0; i < sharks.length; i++) {
            if (sharks[i] == null) continue;
            Shark s = sharks[i];
            int nx = s.r;
            int ny = s.c;
            int direction = s.d;
            int speed = s.s;

            if (direction == 1 || direction == 2) speed %= ((R - 1) * 2);
            if (direction == 3 || direction == 4) speed %= ((C - 1) * 2);

            for (int j = 0; j < speed; j++) {
                nx += dir[direction][0];
                ny += dir[direction][1];

                if (nx < 1 || ny < 1 || nx > R || ny > C) {
                    nx -= dir[direction][0];
                    ny -= dir[direction][1];

                    direction = changeDir[direction];

                    nx += dir[direction][0];
                    ny += dir[direction][1];
                }
            }

            if (map[nx][ny] != 0) {
                if (sharks[map[nx][ny]].z > s.z) { // 겹친 자리에 있는 상어의 크기가 현재 상어의 크기보다 크면
                    sharks[i] = null;    // 현재 상어가 잡아먹힘
                    continue;
                } else { // 겹친 자리에 있는 상어의 크기가 현재 상어의 크기보다 작으면
                    sharks[map[nx][ny]] = null;
                }
            }

            map[nx][ny] = i;
            sharks[i] = new Shark(nx, ny, s.s, direction, s.z);

        }

    }

    static class Fisher {
        int y;
        int sharkSize;

        Fisher(int y, int sharkSize) {
            this.y = y;
            this.sharkSize = sharkSize;
        }

        public void fish(int y) {
            for (int i = 1; i <= R; i++) {
                if (map[i][y] != 0) {
                    this.sharkSize += sharks[map[i][y]].z;
                    sharks[map[i][y]] = null;
                    map[i][y] = 0;
                    break;
                }
            }
        }
    }

    static class Shark {
        int r, c, s, d, z;

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
