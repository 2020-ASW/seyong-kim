package _12월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 준환이의양팔저울 {
    static int N;       // 총 저울의 개수
    static int total;   // 총 저울의 합
    static int ans;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        init();

        for (int tc = 1; tc <= testCase; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            visited = new boolean[N];
            total = 0;
            ans = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                total += arr[i];
            }

            permutation(0, 0, 0, total);

            System.out.println("#" + tc + " " + ans);
        }
    }

    private static void permutation(int cnt, int leftSum, int rightSum, int remain) {
        // 이거 안하면 시간 초과..
        // 남은 추를 모두 오른쪽에 올려도 왼쪽보다 무게가 작은 경우, 탐색하며 하나씩 올려볼 필요 없이
        // 문제에서 주어진 2^N * N!을 이용해 계산
        // N - cnt = 남은 추의 개수
        if (leftSum >= rightSum + remain) {
            ans += pow[N - cnt] * fac[N - cnt];
            return;
        }
        // N개를 모두 뽑았으면 경우의수 +1
        if (cnt == N) {
            ans++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;

            int curWeight = arr[i];

            // 저울 왼쪽에 현재 선택한 추 올리기
            permutation(cnt + 1, leftSum + curWeight, rightSum, remain - curWeight);

            // 선택한 추를 오른쪽에 올렸을 때 왼쪽보다 가벼운 경우에만 올리기
            if (rightSum + curWeight <= leftSum) {
                permutation(cnt + 1, leftSum, rightSum + curWeight, remain - curWeight);
            }
            visited[i] = false;
        }
    }

    static int[] fac = new int[10];
    static int[] pow = new int[10];

    private static void init() {
        pow[0] = 1;
        fac[0] = 1;
        for (int i = 1; i <= 9; i++) {
            pow[i] = pow[i - 1] * 2;
            fac[i] = fac[i - 1] * i;
        }
    }
}
