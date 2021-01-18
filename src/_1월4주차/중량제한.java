package _1월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 중량제한 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Island>[] islands = new ArrayList[N + 1];
        for (int i = 0; i < islands.length; i++) islands[i] = new ArrayList<>();

        int left = 100000, right = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            islands[A].add(new Island(B, C));
            islands[B].add(new Island(A, C));
            left = Math.min(left, C);
            right = Math.max(right, C);
        }
        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(islands, u, v, mid)) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    // targetWeight 가 너무 크면 false
    // 무사히 v에 도착 했으면 true
    private static boolean isPossible(ArrayList<Island>[] islands, int src, int dst, int targetWeight) {
        Queue<Island> queue = new LinkedList<>();
        boolean[] visited = new boolean[islands.length];

        queue.add(new Island(src, 0));
        visited[src] = true;

        while (!queue.isEmpty()) {
            Island now = queue.poll();

            if (now.no == dst) return true;

            for (Island next : islands[now.no]) {
                if (visited[next.no] || next.weightLimit < targetWeight) continue;

                visited[next.no] = true;
                queue.add(next);
            }
        }
        return false;
    }

    static class Island {
        int no, weightLimit;

        Island(int no, int weightLimit) {
            this.no = no;
            this.weightLimit = weightLimit;
        }
    }
}
