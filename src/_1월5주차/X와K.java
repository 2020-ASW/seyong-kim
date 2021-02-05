package _1월5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class X와K {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] binaryX = new int[65];
        for (int i = 64; X > 0; X /= 2) {
            binaryX[i--] = X % 2;
        }

        long result = 0, p = 1;
        for (int i = 64; i > 0; i--) {
            if (K <= 0) break;

            if (binaryX[i] == 0) {
                if (K % 2 == 1) {
                    result += p;
                }
                K /= 2;
            }
            p *= 2;
        }
        System.out.println(result);
    }
}
