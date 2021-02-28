package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬만들기_그리디 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] row = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSum = 0;
        for (int i = 1; i <= N; i++) {
            row[i] = Integer.parseInt(st.nextToken());
            rowSum += row[i];
        }

        int[] col = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int colSum = 0;
        for (int i = 1; i <= N; i++) {
            col[i] = Integer.parseInt(st.nextToken());
            colSum += col[i];
        }

        if (rowSum != colSum) {
            System.out.println(-1);
            return;
        }

        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (row[i] == 0 || col[j] == 0) continue;

                if ((i != N && j != N) && (row[i] - 1 == 0 && col[j] - 1 == 0)) continue;
                map[i][j] = 1;
                row[i]--;
                col[j]--;
            }
        }
        System.out.println(1);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
