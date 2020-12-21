package _12월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 햄버거다이어트 {

    static int N, L;
    static Material[] materials;
    static int max;

    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            materials = new Material[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int point = Integer.parseInt(st.nextToken());
                int calorie = Integer.parseInt(st.nextToken());
                materials[i] = new Material(point, calorie);
            }
            max = 0;
            dfs(0, 0, 0);
            System.out.println("#" + testCase + " " + max);
        }
    }
    private static void dp(){

    }

    private static void dfs(int idx, int sumPoint, int sumCalorie) {
        if (sumCalorie > L) return;
        if (idx == N) {
            max = Math.max(max, sumPoint);
            return;
        }

        dfs(idx + 1, materials[idx].point + sumPoint, materials[idx].calorie + sumCalorie);
        dfs(idx + 1, sumPoint, sumCalorie);
    }

    static class Material {
        int point;
        int calorie;

        Material(int point, int calorie) {
            this.point = point;
            this.calorie = calorie;
        }
    }
}
