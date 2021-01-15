package _1월3주차;

public class 자동완성 {
    public static int solution(String[] words) {
        int answer = 0;

        Trie trie = new Trie();
        for (String word : words) trie.insert(word);

        for (String word : words) answer += trie.getLeastTyping(word);

        return answer;
    }

    static class Trie {
        private final TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;

            for (int i = 0; i < word.length(); i++) {
                int next = word.charAt(i) - 'a';

                if (current.children[next] == null)
                    current.children[next] = new TrieNode();
                else
                    current.children[next].count += 1;

                current = current.children[next];
            }
        }

        public int getLeastTyping(String word) {
            TrieNode current = root;

            int count = 0;

            for (int i = 0; i < word.length(); i++) {
                int next = word.charAt(i) - 'a';

                count++;

                if (current.children[next].count == 1)
                    return count;

                current = current.children[next];
            }
            return count;
        }

    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26]; // a~z
        int count;

        TrieNode() {
            count = 1;
        }
    }
}
