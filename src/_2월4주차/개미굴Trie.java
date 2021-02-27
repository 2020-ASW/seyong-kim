package _2월4주차;

import java.io.*;
import java.util.*;

public class 개미굴Trie {
    static final String HYPHEN = "--";
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie antHouse = new Trie();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            String[] input = new String[k];

            int idx = 0;
            while (st.hasMoreTokens()) {
                input[idx++] = st.nextToken();
            }
            antHouse.insert(input);
        }

        TrieNode entrance = antHouse.root;

        sb = new StringBuilder();
        antHouse.show(entrance, 0);
        System.out.println(sb.toString());
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String[] foods) {
            TrieNode cur = root;

            for (String food : foods) {
                if (!cur.map.containsKey(food)) cur.map.put(food, new TrieNode());

                cur = cur.map.get(food);
            }
        }

        public void show(TrieNode cur, int depth) {
            for (String food : cur.map.keySet()) {

                for (int i = 0; i < depth; i++) {
                    sb.append(HYPHEN);
                }
                sb.append(food).append("\n");

                TrieNode next = cur.map.get(food);
                show(next, depth + 1);
            }
        }
    }

    static class TrieNode {
        TreeMap<String, TrieNode> map;

        public TrieNode() {
            map = new TreeMap<>();
        }
    }
}