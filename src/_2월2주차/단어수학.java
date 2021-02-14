package _2월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class 단어수학 {
    static int N, answer;
    static String[] words;
    static ArrayList<String> alphabets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        words = new String[N];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            for (char ch : words[i].toCharArray())
                map.put(ch + "", null);
        }
        answer = 0;
        alphabets = new ArrayList<>(map.keySet());
        dfs(9, map, 0);

        System.out.println(answer);
    }

    private static void dfs(int idx, Map<String, Integer> map, int cnt) {
        if (cnt == map.size()) {
            int val = calculate(map);
            answer = Math.max(answer, val);
            return;
        }

        for (int i = idx; i >= 9 - map.size(); i--) {
            for (String ch : alphabets) {
                if (map.get(ch) != null) continue;

                map.put(ch, i);
                dfs(i - 1, map, cnt + 1);
                map.put(ch, null);
            }
        }
    }

    private static int calculate(Map<String, Integer> map) {
        int sum = 0;
        String[] cloneArr = words.clone();
        for (String word : cloneArr) {
            int len = word.length();
            int k = len - 1;
            while (k >= 0) {
                int num = map.get(word.substring(len - 1 - k, len - k));
                sum += num * Math.pow(10, k);
                k--;
            }
        }
        return sum;
    }
}
