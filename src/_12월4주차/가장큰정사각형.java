package _12월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 가장큰정사각형 {
    static int n, m;
    static int[][] map;
    static int[][] direction = {{0, -1}, {-1, -1}, {-1, 0}}; // 왼, 왼위, 위

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mapSize = br.readLine().split(" ");
        n = Integer.parseInt(mapSize[0]);
        m = Integer.parseInt(mapSize[1]);
        map = new int[n][m];

        /*
        for (int i = 0; i < n; i++) {
            String[] mArr = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(mArr[j]);
            }
        }*/

        for (int i = 0; i < n; i++) {
            String mArr = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = mArr.charAt(j) - '0';
            }
        }

        int max = map[0][0];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // 현재 칸이 0일 경우
                if (map[i][j] == 0) continue;

                int val = Integer.MAX_VALUE;

                // 왼쪽, 왼쪽 위, 위쪽 탐색
                for (int[] dir : direction) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];

                    if(map[nx][ny] == 0) {
                        val = 0;
                        break;
                    }

                    val = Math.min(val, map[nx][ny]);
                }
                map[i][j] = val + 1;
                max = Math.max(max, map[i][j]);
            }
        }
        System.out.println(max * max);
    }
}
