package _1월1주차;

import java.io.*;
import java.util.*;

public class 작업순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = 10;

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[] indegree = new int[V + 1];
            ArrayList<Integer>[] list = new ArrayList[V + 1];

            for (int i = 0; i < list.length; i++) {
                list[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                indegree[b]++;
            }

            sb.append("#")
                    .append(testCase)
                    .append(" ")
                    .append(topologicalSort(list, indegree));
        }
        System.out.println(sb.toString());
    }

    private static String topologicalSort(ArrayList<Integer>[] list, int[] indegree) {
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.append(vertex).append(" ");

            for (int i = 0; i < list[vertex].size(); i++) {
                int next = list[vertex].get(i);
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return result.toString();
    }
}
