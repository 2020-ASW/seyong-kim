package _1월4주차;

import java.util.*;

public class 풍선터트리기 {
    public int solution(int[] a) {
        if (a.length == 1) return 1;

        Set<Integer> result = new HashSet<>();

        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;

        for (int i = 0; i < a.length; i++) {
            leftMin = Math.min(leftMin, a[i]);
            rightMin = Math.min(rightMin, a[a.length-1-i]);
            result.add(leftMin);
            result.add(rightMin);
        }

        return result.size();
    }
}
