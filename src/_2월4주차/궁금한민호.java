package _2월4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 궁금한민호 {
    static final int DISCONNECTED = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N + 1];
        int[][] origin = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == k || j == k) continue;

                    if (map[i][j] > map[i][k] + map[k][j]) {
                        System.out.println(-1);
                        return;
                    }

                    if (map[i][j] == map[i][k] + map[k][j]) {
                        origin[i][j] = DISCONNECTED;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                if (i >= j) answer += origin[i][j];
            }
        }
        System.out.println(answer);
    }
}
