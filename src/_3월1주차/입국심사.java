package _3월1주차;

import java.util.Arrays;

class 입국심사 {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long max = times[times.length - 1];
        long answer = max * n;
        long left = 1, right = max * n;
        // long right = n * times[times.length - 1];
        // 이렇게 할 경우 int*int라 오버플로우

        while (left <= right) {
            long mid = (left + right) / 2;

            if (isPossible(times, n, mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    private boolean isPossible(int[] times, int n, long mid) {
        long count = 0;
        for (int time : times) {
            count += mid / time;
        }
        return count >= n;
    }
}