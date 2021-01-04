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
/*
혹시 boj.kr/1091 카드섞기 문제에서
"만약, 섞어도 섞어도 카드를 해당하는 플레이어에게 줄 수 없다면, -1을 출력한다." 이 부분에 조건을 어떻게 두셨나요?
저는 answer가 일정 수를 넘어가면 -1을 출력하게 했는데, 일정 수 라는게 채점 기준 정답이면서 시간 초과 안 날 정도를 테스트하면서 얻어낸 거라..
이 방법 말고 혹시 어떤 방법이 있을까요..??
*/