package _1월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카드섞기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] P = new int[49 * 2];
        int[] S = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) P[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        while (true) {
            if (check(P, N)) break;

            shuffle(P, S, N);
            answer++;

            if (answer > 1000000) {
                answer = -1;
                break;
            }
        }
        System.out.println(answer);
    }

    private static void shuffle(int[] P, int[] S, int N) {
        for (int i = 0; i < N; i++) {
            P[N + S[i]] = P[i];
        }
        for (int i = 0; i < N; i++) {
            P[i] = P[N + i];
        }
    }

    private static boolean check(int[] P, int N) {
        for (int i = 0; i < N; i++) {
            if (P[i] != i % 3) {
                return false;
            }
        }
        return true;
    }
}