package _3월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 세용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        boolean flag = false;
        long[] result = new long[3];
        long diff = 3000000000L;
        for (int x = 0; x < N - 2; x++) {
            long w1 = arr[x];

            int left = x + 1;
            int right = N - 1;
            while (left < right) {
                long w2 = arr[left];
                long w3 = arr[right];

                long sum = w1 + w2 + w3;
                if (diff > Math.abs(sum)) {
                    diff = Math.abs(sum);
                    result = new long[]{w1, w2, w3};
                }

                if (sum == 0) {
                    flag = true;
                    break;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
            if (flag) break;
        }

        Arrays.sort(result);
        System.out.println(Arrays.toString(result));
    }
}
