package _12월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 이미지유사도검사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= TC; testCase++) {
            int N = Integer.parseInt(br.readLine().trim());
            String X = br.readLine();
            String Y = br.readLine();

            if (X.length() == 0 || Y.length() == 0) {
                System.out.println("#" + testCase + " " + 0.00);
                continue;
            }

            int[][] dp = new int[N + 1][N + 1];
            int LCS = 0;
            for (int i = 1; i <= N; i++) {       // X
                for (int j = 1; j <= N; j++) {   // Y
                    if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }

                    LCS = Math.max(LCS, dp[i][j]);
                }
            }

            double result = (double) LCS / N * 100;
            System.out.println(String.format("#%s %.2f", testCase, result));
        }
    }
}

/*
for (int i = 0; i < N; i++) {       // X
    for (int j = 0; j < N; j++) {   // Y
        char word = X.charAt(i);

        if (i == 0 && j == 0) {
            dp[i][j] = (word == Y.charAt(j)) ? 1 : 0;
            continue;
        }

        if (i == 0) {
            dp[i][j] = (word == Y.charAt(j)) ? dp[i][j - 1] + 1 : dp[i][j - 1];
            continue;
        }

        if (j == 0) {
            dp[i][j] = (word == Y.charAt(j)) ? dp[i - 1][j] + 1 : dp[i - 1][j];
            continue;
        }

        // 0 < i , j
        if (word == Y.charAt(j)) {
            dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + 1;
        } else {
            dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);

        }
    }
}
*/