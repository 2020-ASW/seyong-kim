package _1월4주차;

import java.util.*;

public class 보석쇼핑 {
    public static int[] solution(String[] gems) {
        Set<String> set = new HashSet<>(Arrays.asList(gems));

        int totalGem = set.size();

        Map<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        int start = 0;
        int resStart = 0, length = Integer.MAX_VALUE;
        for (int i = 0; i < gems.length; i++) {
            if (!map.containsKey(gems[i])) {
                map.put(gems[i], 1);
            } else {
                map.put(gems[i], map.get(gems[i]) + 1);
            }

            queue.add(gems[i]);

            while (!queue.isEmpty()) {
                if (map.get(queue.peek()) >= 2) {
                    String dupGem = queue.poll();
                    map.put(dupGem, map.get(dupGem) - 1);
                    start++;
                } else {
                    break;
                }
            }

            if (totalGem == map.size() && length > queue.size()) {
                length = queue.size();
                resStart = start;
            }
        }

        return new int[]{resStart + 1, resStart + length};
    }

    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(Arrays.toString(solution(gems)));

        gems = new String[]{"AA", "AB", "AC", "AA", "AC"};
        System.out.println(Arrays.toString(solution(gems)));

        gems = new String[]{"XYZ", "XYZ", "XYZ"};
        System.out.println(Arrays.toString(solution(gems)));

        gems = new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        System.out.println(Arrays.toString(solution(gems)));

        gems = new String[]{"DIA", "EM", "EM", "RUB", "DIA"};
        System.out.println(Arrays.toString(solution(gems)));
    }
}
