package _2월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class K진트리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            long distance = 0;
            if (K == 1) {
                distance = Math.abs(x - y);
                sb.append(distance).append("\n");
                continue;
            }
            while (x != y) {
                if (x < y)
                    y = getParent(y, K);
                else
                    x = getParent(x, K);
                distance++;
            }
            sb.append(distance).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static long getParent(long n, int K) {
        return (n - 2) / K + 1;
    }
}
