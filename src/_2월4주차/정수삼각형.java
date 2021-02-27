package _2월4주차;

public class 정수삼각형 {
    public static int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle[triangle.length - 1].length];


        int[][] direction = {{-1, 0}, {-1, -1}};
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                int tmp = 0;
                for (int[] dir : direction) {
                    int px = i + dir[0];
                    int py = j + dir[1];

                    if (py < 0) continue;

                    tmp = Math.max(tmp, triangle[i][j] + dp[px][py]);
                }
                dp[i][j] = tmp;
            }
        }
        int max = 0;
        for (int n : dp[dp.length - 1])
            max = Math.max(max, n);

        return max;
    }

    public static void main(String[] args) {
        int[][] input = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(input));
    }
}
