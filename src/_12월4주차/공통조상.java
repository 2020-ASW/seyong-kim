package _12월4주차;

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
            V = Integer.parseInt(st.nextToken());   // 트리의 정점의 총 개수 (10 <= V <= 10,000)
            E = Integer.parseInt(st.nextToken());   // 간선의 총 개수
            A = Integer.parseInt(st.nextToken());   // 공통 조상을 찾는 임의의 정점 1
            B = Integer.parseInt(st.nextToken());   // 공통 조상을 찾는 임의의 정점 2

            tree = new Node[V + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                if (tree[parent] == null)
                    tree[parent] = new Node();
                if (tree[child] == null)
                    tree[child] = new Node();

                tree[parent].connectWith(child);
                tree[child].connectWith(parent);
                tree[child].setParent(parent);
            }

            int cnt = 0;
            boolean[] visited = new boolean[V + 1];

            int sameParent = 1;
            while (true) {
                // A와 B의 부모를 확인 하면서(위로 올라가면서) 방문한 적이 있는 노드일 경우 가장 가까운 공통 부모라고 판단
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

            int nodeCount = getNodeCount(sameParent);
            System.out.println("#" + testCase + " " + sameParent + " " + nodeCount);
        }
    }

    private static int getNodeCount(int curNode) {
        if (tree[curNode].linkedNodes.size() - 1 == 0) { // 연결된 노드가 부모 제외하고 0이라면 리프 노드
//            System.out.println(curNode + " == 리프 노드");
            return 1;
        }

        int cnt = 1;
        for (int node : tree[curNode].linkedNodes) {

            if (tree[curNode].parent == node) continue;

            cnt += getNodeCount(node);
        }
//        System.out.println(curNode + "의 자식의 개수 = " + (cnt - 1));
        return cnt;
    }

    static class Node {
        private ArrayList<Integer> linkedNodes;
        int parent;

        Node() {
            parent = -1;
            this.linkedNodes = new ArrayList<>();
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public void connectWith(int nodeNumber) {
            this.linkedNodes.add(nodeNumber);
        }
    }
}
