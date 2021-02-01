package _2월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 셀로판지 {
    static int p, q, r;
    static int a, b, c, d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= TC; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            String result;
            if (isYN()) result = "YN";
            else if (isNY()) result = "NY";
            else result = "YY";

            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean isYN() {
        if (isNotIncludedInCircle(a, b)) return false;
        if (isNotIncludedInCircle(a, d)) return false;
        if (isNotIncludedInCircle(c, b)) return false;
        if (isNotIncludedInCircle(c, d)) return false;
        return true;
    }

    private static boolean isNotIncludedInCircle(int i, int j) {
        return Math.pow(p - i, 2) + Math.pow(q - j, 2) > Math.pow(r, 2);
    }

    private static boolean isNY() {
        if (p + r > c) return false;
        if (p - r < a) return false;
        if (q + r > d) return false;
        if (q - r < b) return false;
        return true;
    }

}
