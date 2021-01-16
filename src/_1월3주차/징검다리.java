package _1월3주차;

import java.util.*;

public class 징검다리 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);
        int l = 0, r = distance;

        while (l <= r) {
            int mid = (l + r) / 2;      // 각 징검다리 사이 거리의 최소값 중 가장 큰 값
            int remove = 0;             // 제거한 징검다리의 수

            // 각 지점 사이 거리 확인
            int preRock = 0;
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - preRock < mid)   // 기대하는 값보다 지점 사이의 거리가 작으면 해당 돌 제거
                    remove++;
                else                            // 사이 거리를 계산할 기준 지점 갱신
                    preRock = rocks[i];
            }
            // 마지막 지점과 도착 지점 사이 거리
            if (distance - rocks[rocks.length - 1] < mid) remove++;

            // n 보다 제거한 돌이 많은 경우 = mid 값을 너무 크게 잡았기 때문
            if (remove > n)
                r = mid - 1;
            else {
                answer = Math.max(answer, mid);
                l = mid + 1;
            }
        }

        return answer;
    }
}
