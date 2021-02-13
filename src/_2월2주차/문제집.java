package _2월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 문제집 {
    static int N, M;
    static ArrayList<Integer>[] list;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 1; i < list.length; i++) list[i] = new ArrayList();

        indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            list[A].add(B);
            indegree[B]++;
        }

        System.out.println(topologicalSort(list, indegree));
    }

    private static String topologicalSort(ArrayList<Integer>[] list, int[] indegree) {
        PriorityQueue<Integer> queue = new PriorityQueue();
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
