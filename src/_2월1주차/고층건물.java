package _2월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 고층건물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] buildings = new int[N];
        int[] cnt = new int[N];

        for (int i = 0; i < N; i++) buildings[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            double preTan = -1000000000;

            for (int j = i + 1; j < N; j++) {
                double tan = 1.0 * (buildings[j] - buildings[i]) / (j - i);

                if (preTan < tan) {
                    preTan = tan;
                    cnt[i]++;  cnt[j]++; // 서로 볼 수 있음
                }
            }
        }
        int answer = 0;
        for (int val : cnt) answer = Math.max(answer, val);

        System.out.println(answer);
    }
}
