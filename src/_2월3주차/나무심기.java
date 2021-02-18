package _2월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 나무심기 {
    static int N;
    static int MOD = 1000000007;
    static int[] arr, cntArr;
    static long segmentTree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        int limit = -1;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            limit = Math.max(limit, arr[i]);
        }
        int x = (int) Math.ceil(Math.log(200000)/Math.log(2));
        int size = (int) Math.pow(2, x + 1);
        cntArr = new int[size];
        segmentTree = new long[size];

        long mul = 1;
        for (int i = 0; i < N; i++) {
            update(1, 0, limit, arr[i]);

            long left = getCNT(0, limit, 1, 0, arr[i]) * arr[i] - getSum(0, limit, 1, 0, arr[i]);
            long right = getSum(0, limit, 1, arr[i] + 1, limit) - getCNT(0, limit, 1, arr[i] + 1, limit) * arr[i];

            long result = (left + right) % MOD;

            if (i != 0) mul = (mul * result) % MOD;
        }
        System.out.println(mul);
    }

    public static void update(int node, int start, int end, int idx) {
        if (!(start <= idx && idx <= end)) return;

        cntArr[node]++;
        segmentTree[node] += idx;

        if (start == end) return;

        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx);
        update(node * 2 + 1, mid + 1, end, idx);

    }

    public static long getCNT(int start, int end, int node, int left, int right) {
        if (start > right || end < left)
            return 0;

        if (left <= start && end <= right)
            return cntArr[node];

        int mid = (start + end) / 2;

        return getCNT(start, mid, node * 2, left, right) + getCNT(mid + 1, end, node * 2 + 1, left, right);
    }

    public static long getSum(int start, int end, int node, int left, int right) {
        if (start > right || end < left)
            return 0;

        if (left <= start && end <= right)
            return segmentTree[node];

        int mid = (start + end) / 2;

        return getSum(start, mid, node * 2, left, right) + getSum(mid + 1, end, node * 2 + 1, left, right);
    }
}
