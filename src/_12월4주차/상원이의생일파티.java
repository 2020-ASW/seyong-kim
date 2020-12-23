package _12월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상원이의생일파티 {
    static int N;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= TC; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            graph = new int[N + 1][N + 1];
            visited = new boolean[N + 1];

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = 1;
                graph[b][a] = 1;
            }

            int friends = bfs(new Student(1, 0));

            System.out.println("#" + testCase + " " + friends);
        }
    }

    private static int bfs(Student sangwon) {
        Queue<Student> queue = new LinkedList<>();
        queue.add(sangwon);
        visited[sangwon.no] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            Student student = queue.poll();

            for (int i = 2; i <= N; i++) {
                if (graph[student.no][i] == 0) continue;
                if (visited[i]) continue;
                if (student.distance >= 2) continue;

                queue.add(new Student(i, student.distance + 1));
                visited[i] = true;
                count++;
            }
        }
        return count;
    }

    static class Student {
        int no;
        int distance;

        Student(int no, int distance) {
            this.no = no;
            this.distance = distance;
        }
    }
}
