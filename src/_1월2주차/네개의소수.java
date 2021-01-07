package _1월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 네개의소수 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N < 8) {
            System.out.println(-1);
            return;
        }

        boolean[] primeArr = new boolean[N + 1];
        Arrays.fill(primeArr, true);
        setPrimeNumber(primeArr);

        if (N % 2 == 0) {
            N -= 4;
            System.out.print("2 2 ");
        } else {
            N -= 5;
            System.out.print("2 3 ");
        }
        for (int i = 2; i <= primeArr.length; i++) {
            if (primeArr[i] && primeArr[N - i]) {
                System.out.println(i + " " + (N - i));
                break;
            }
        }
    }

    private static void setPrimeNumber(boolean[] prime) {
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; i <= N; i++) {
            if (!prime[i])
                continue;
            for (int j = i + i; j <= N; j += i)
                prime[j] = false;
        }
    }
}