package _12월3추자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class A와B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());

        int sLen = S.length();
        int tLen = T.length();

        while (tLen > sLen) {
            if (T.charAt(tLen - 1) == 'A') {
                T.deleteCharAt(tLen - 1);
                tLen--;
            } else {
                T.deleteCharAt(tLen - 1);
                tLen--;
                T.reverse();
            }
        }
        int result = S.equals(T.toString()) ? 1 : 0;
        System.out.println(result);
    }

}
