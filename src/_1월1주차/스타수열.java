package _1월1주차;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class 스타수열 {
    public static int solution(int[] a) {
        int answer = -1;
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            if (!frequency.containsKey(a[i]))
                frequency.put(a[i], 1);
            else
                frequency.put(a[i], frequency.get(a[i]) + 1);
        }
        for (int commonElement : frequency.keySet()) {
            if (frequency.get(commonElement) <= answer) continue;
            int result = 0;

            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] != commonElement && a[i + 1] != commonElement) continue;
                if (a[i] == a[i + 1]) continue;

                // 공통 원소가 있고, 인접한 두 원소가 다른 경우
                result++;
                i++;
            }
            answer = Math.max(answer, result);
        }

        return answer * 2;
    }

    public static void main(String[] args) {
//        int[] a = {5, 2, 3, 3, 5, 3};
        int[] a = {0, 3, 3, 0, 7, 2, 0, 2, 2, 0};

    }
}
