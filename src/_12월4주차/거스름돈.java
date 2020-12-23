package _12월4주차;

public class 거스름돈 {
    public static int solution(int n, int[] money) {
        int[] dp = new int[n + 1];

        dp[0] = 1;  // 0원을 만들 수 있는 방법은 1가지

        // '거스름돈 n원을 만들 수 있는 경우의 수'
        // D[n] = D[n] + D[n - 사용할 동전의 단위]
        for (int m : money) {
            for (int i = m; i <= n; i++) {
                dp[i] += dp[i - m];
            }
        }
        return dp[n] % 1000000007;
    }

    public static void main(String[] args) {
        System.out.println(solution(10, new int[]{2, 3, 5}));
        // 2*5
        // 2*2 3*2
        // 2*1 3*1 5*1
        // 5*2
    }

}
