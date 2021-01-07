package _1월2주차;

import java.util.Arrays;

public class 최고의집합 {
    public int[] solution(int n, int s) {
        if (n > s) return new int[]{-1};

        int[] answer = new int[n];
        Arrays.fill(answer, s / n);

        int k = s % n;
        if (k == 0)
            return answer;

        for (int i = 0; i < k; i++)
            answer[n - 1 - i] += 1;

        return answer;
    }
}
