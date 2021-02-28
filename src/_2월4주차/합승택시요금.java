package _2월4주차;

import java.util.Arrays;

// INF를 Integer.MAX_VALUE로 설정할 시 합산하면서 쓰레기값 나오니 조심..
public class 합승택시요금 {
    static final int INF = 20000000;

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] map = new int[n + 1][n + 1];

        for (int i = 1; i < map.length; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int[] fare : fares) {
            map[fare[0]][fare[1]] = fare[2];
            map[fare[1]][fare[0]] = fare[2];
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        int answer = INF;

        for (int k = 1; k <= n; k++) {
            answer = Math.min(answer, map[s][k] + map[k][a] + map[k][b]);
        }

        return answer;
    }

    public static void print(int[][] map) {
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[i].length; j++) {
                String s = (map[i][j] != INF) ? String.valueOf(map[i][j]) : "INF";
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
