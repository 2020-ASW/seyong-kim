package _1월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 공통조상 {
    static int V, E, A, B;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= TC; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            tree = new Node[V + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                if (tree[parent] == null)
                    tree[parent] = new Node();
                if (tree[child] == null)
                    tree[child] = new Node();

                tree[parent].linkedNodes.add(child);
                tree[child].linkedNodes.add(parent);
                tree[child].parent = parent;
            }

            boolean[] visited = new boolean[V + 1];

            int sameParent = 1;
            while (true) {
                if (A != 1) {
                    int parentOfA = tree[A].parent;
                    if (visited[parentOfA]) {
                        sameParent = parentOfA;
                        break;
                    }
                    visited[parentOfA] = true;
                    A = parentOfA;
                }
                if (B != 1) {
                    int parentOfB = tree[B].parent;
                    if (visited[parentOfB]) {
                        sameParent = parentOfB;
                        break;
                    }
                    visited[parentOfB] = true;
                    B = parentOfB;
                }
            }
            String res = "#" + testCase +
                         " " + sameParent +
                         " " + getNodeCount(sameParent);
            System.out.println(res);
        }
    }

    private static int getNodeCount(int curNode) {
        if (tree[curNode].linkedNodes.size() - 1 == 0) return 1;

        int cnt = 1;
        for (int node : tree[curNode].linkedNodes) {
            if (tree[curNode].parent == node) continue;

            cnt += getNodeCount(node);
        }
        return cnt;
    }

    static class Node {
        public ArrayList<Integer> linkedNodes;
        int parent;

        Node() {
            parent = -1;
            this.linkedNodes = new ArrayList<>();
        }
    }
}
