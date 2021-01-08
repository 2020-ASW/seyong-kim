package _1월1주차;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class 스타수열 {
    public static int solution(int[] a) {
        int answer = -1;

        // 각 원소의 등장 횟수를 저장할 Map
        // < 원소 , 등장 횟수 >
        Map<Integer, Integer> frequency = new HashMap<>();

        // 등장 횟수 카운트
        for (int i = 0; i < a.length; i++) {
            if (!frequency.containsKey(a[i]))
                frequency.put(a[i], 1);
            else
                frequency.put(a[i], frequency.get(a[i]) + 1);
        }

        // commonElement = 공통 원소가 될 숫자
        for (int commonElement : frequency.keySet()) {
            // 공통 원소의 등장 횟수가 현재 최대 묶음 집합의 개수보다 작다면 처리 안해
            if (frequency.get(commonElement) <= answer) continue;

            // 묶음 집합(2개씩 묶은 집합)의 개수
            int result = 0;
            for (int i = 0; i < a.length - 1; i++) {
                // 묶음 집합에 공통 원소가 없으면 안돼
                if (a[i] != commonElement && a[i + 1] != commonElement) continue;
                // 묶음 집합의 두 원소가 같아도 안돼
                if (a[i] == a[i + 1]) continue;

                // 공통 원소가 있고, 인접한 두 원소가 다른 경우
                result++;
                i++;
            }
            answer = Math.max(answer, result);
        }
        // 스타 수열의 길이 = 묶음 집합의 개수x2
        return answer * 2;
    }

    public static void main(String[] args) {
//        int[] a = {5, 2, 3, 3, 5, 3};
        int[] a = {0, 0, 0, 0, 0, 0, 1, 2, 1, 3};
        System.out.println(Arrays.toString(a));
        System.out.println("답:" + solution(a));
    }
}