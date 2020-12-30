package _1월1주차;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 친구네트워크 {

    static int[] parent;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            int F = Integer.parseInt(br.readLine());

            Map<String, Integer> map = new HashMap<>();
            parent = new int[F * 2 + 1];
            cnt = new int[F * 2 + 1];

            int index = 1;
            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();

                if (!map.containsKey(friend1)) {
                    map.put(friend1, index);
                    parent[index] = index;
                    cnt[index++] = 1;
                }
                if (!map.containsKey(friend2)) {
                    map.put(friend2, index);
                    parent[index] = index;
                    cnt[index++] = 1;
                }

                union(map.get(friend1), map.get(friend2));

                int y = map.get(friend2);

                bw.write(cnt[find(y)] + "\n");
            }

        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        parent[y] = x;
        cnt[x] += cnt[y];
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }
}