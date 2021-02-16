package _2월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][K + 1];
        int[][] items = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            items[i][0] = W;
            items[i][1] = V;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                // i번째 물건을 넣을 수 있다면
                if (j - items[i][0] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i][0]] + items[i][1]);
                    continue;
                }
                // i번째 물건을 넣지 않았을 때
                dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[N][K]);
    }

    // 공간 복잡도 최적화 코드
    /*
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];
        int[][] items = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            items[i][0] = W;
            items[i][1] = V;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = K; j >= 1; j--) {
                if (items[i][0] <= j)
                    dp[j] = Math.max(dp[j], dp[j - items[i][0]] + items[i][1]);
            }
        }
        System.out.println(dp[K]);
    }
    */
}
