package _2월3주차;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 사칙연산 {
    public static int solution(String arr[]) {
        List<String> nums = new ArrayList<>();
        List<String> ops = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) nums.add(arr[i]);
            else ops.add(arr[i]);
        }

        int[][] minDP = new int[nums.size()][nums.size()];
        int[][] maxDP = new int[nums.size()][nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            Arrays.fill(minDP[i], Integer.MAX_VALUE);
            Arrays.fill(maxDP[i], Integer.MIN_VALUE);
        }

        for (int i = 0; i < nums.size(); i++) {
            minDP[i][i] = maxDP[i][i] = Integer.parseInt(nums.get(i));
        }

        int n = nums.size();
        for (int pos = 1; pos < n; pos++) {
            for (int start = 0; start < n - pos; start++) {
                int end = start + pos;

                for (int k = start; k < end; k++) {
                    if (arr[k * 2 + 1].equals("+")) {
                        maxDP[start][end] = Math.max(maxDP[start][k] + maxDP[k + 1][end], maxDP[start][end]);
                        minDP[start][end] = Math.min(minDP[start][k] + minDP[k + 1][end], minDP[start][end]);

                    } else {
                        maxDP[start][end] = Math.max(maxDP[start][k] - minDP[k + 1][end], maxDP[start][end]);
                        minDP[start][end] = Math.min(minDP[start][k] - maxDP[k + 1][end], minDP[start][end]);

                    }
                }
            }
        }

        return maxDP[0][nums.size() - 1];
    }

    public static void main(String[] args) {
        solution(new String[]{"1", "-", "3", "+", "5", "-", "8"});
    }
}
