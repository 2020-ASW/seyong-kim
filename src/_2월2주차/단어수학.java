package _2월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class 단어수학 {
    static int N, answer;
    static char[][] words;
    static boolean[] visited;
    static ArrayList<Character> alphabets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new char[N][];

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            words[i] = tmp;
            for (char ch : tmp) map.put(ch, null);
        }
        alphabets = new ArrayList<>(map.keySet());

        visited = new boolean[10];
        answer = 0;

        dfs(map, 0);

        System.out.println(answer);
    }

    private static void dfs(Map<Character, Integer> map, int cnt) {
        if (cnt == map.size()) {
            int val = calculate(map);
            answer = Math.max(answer, val);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            map.put(alphabets.get(cnt), i);

            dfs(map, cnt + 1);

            visited[i] = false;
        }
    }

    private static int calculate(Map<Character, Integer> map) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int tmp = 0;
            for (char ch : words[i]) {
                tmp *= 10;
                tmp += map.get(ch);
            }
            sum += tmp;
        }
        return sum;
    }
}
