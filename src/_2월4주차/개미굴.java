package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 개미굴 {
    static TreeMap<String, TreeMap> map;
    static StringBuilder sb;
    static final String HYPHEN = "--";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        map = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            TreeMap<String, TreeMap> cur = map;
            for (int j = 0; j < n; j++) {
                String food = st.nextToken();

                if (!cur.containsKey(food))
                    cur.put(food, new TreeMap<>());

                cur = (TreeMap<String, TreeMap>) cur.get(food);
            }
        }
        sb = new StringBuilder();
        drawAntHouse(map, 0);
        System.out.println(sb.toString());
    }

    private static void drawAntHouse(TreeMap<String, TreeMap> map, int depth) {
        for (String food : map.keySet()) {
            for (int i = 0; i < depth; i++) sb.append(HYPHEN);

            sb.append(food).append("\n");
            drawAntHouse(map.get(food), depth + 1);
        }
    }
}
