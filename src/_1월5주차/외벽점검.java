package _1월5주차;

import java.util.ArrayList;

public class 외벽점검 {
    static int N, F, answer;
    static int[] map;
    static boolean[] visited;

    public static int solution(int n, int[] weak, int[] dist) {
        N = n;              // 벽 길이
        F = dist.length;    // 총 친구 수

        map = new int[weak.length];
        for (int i = 0; i < weak.length; i++) {
            if (i == weak.length - 1) {
                map[i] = weak[0] + n - weak[i];
                continue;
            }
            map[i] = weak[i + 1] - weak[i];
        }

        answer = Integer.MAX_VALUE;

        for (int i = 1; i <= dist.length; i++) {
            visited = new boolean[dist.length];
            permutation(dist, i, new ArrayList<>());
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static void permutation(int[] dist, int max, ArrayList<Integer> friends) {
        if (friends.size() == max) {
            int val = checkWall(friends);
            answer = Math.min(answer, val);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) continue;

            friends.add(dist[i]);
            visited[i] = true;
            permutation(dist, max, friends);
            visited[i] = false;
            friends.remove(friends.size() - 1);
        }
    }

    private static int checkWall(ArrayList<Integer> friends) {
        int count = 0;
        for (int start = 0; start < map.length; start++) {
            boolean flag = true;
            int idx = 0;
            int sum = 0;
            for (int i = start; i < start + map.length; i++) {
                int cur = map[i % map.length];

                sum += cur;
                if (friends.get(idx) < sum) {
                    idx++;
                    sum = 0;
                    count++;
                    if (idx >= friends.size()) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                return count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
        System.out.println(solution(12, new int[]{0, 10}, new int[]{1, 2}));
        System.out.println(solution(200, new int[]{0, 100}, new int[]{1, 1}));
        System.out.println(solution(200, new int[]{0, 10, 50, 80, 120, 160}, new int[]{1, 10, 5, 40, 30}));
    }
}























