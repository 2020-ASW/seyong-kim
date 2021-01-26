package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리의지름 {
    static int V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());

        ArrayList<Node>[] tree = new ArrayList[V + 1];
        for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            String[] input = br.readLine().split(" ");

            int u = Integer.parseInt(input[0]);

            for (int j = 1; j <= input.length - 2; j += 2) {
                int v = Integer.parseInt(input[j]);
                int d = Integer.parseInt(input[j + 1]);
                tree[u].add(new Node(v, d));
            }
        }
        Node firstEnd = bfs(tree, new Node(1,0));
        Node secondEnd = bfs(tree, new Node(firstEnd.no, 0));

        System.out.println(secondEnd.distance);
    }

    private static Node bfs(ArrayList<Node>[] tree, Node src) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[tree.length];
        queue.add(src);
        visited[src.no] = true;

        Node farthestNode = new Node(0, 0);

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (farthestNode.distance < now.distance) {
                farthestNode.no = now.no;
                farthestNode.distance = now.distance;
            }

            for (Node next : tree[now.no]) {
                if (visited[next.no]) continue;
                visited[next.no] = true;
                queue.add(new Node(next.no, now.distance + next.distance));
            }
        }
        return farthestNode;
    }

    static class Node {
        int no, distance;

        Node(int no, int distance) {
            this.no = no;
            this.distance = distance;
        }
    }
}
