package _12월4주차;

public class 자동완성 {
    public static int solution(String[] words) {

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int answer = 0;
        for (String word : words) {
            answer += trie.getMinimumTypingCount(word);
        }

        return answer;
    }

    static class Trie {
        private TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curNode = root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int next = ch - 'a';

                if (curNode.children[next] == null) {
                    curNode.children[next] = new TrieNode();
                } else {
                    curNode.children[next].count++;
                }

                curNode = curNode.children[next];

            }
        }

        // get minimum typing counting for finding unique word.
        public int getMinimumTypingCount(String word) {
            TrieNode curNode = root;

            int leastTyping = 0;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int next = ch - 'a';

                leastTyping++;

                if (curNode.children[next].count == 1) {
                    return leastTyping;
                }

                curNode = curNode.children[next];
            }

            return leastTyping;
        }
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26]; // a~z
        int count;  // how many used

        TrieNode() {
            count = 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"go", "gone", "guild"}));
        System.out.println(solution(new String[]{"abc", "def", "ghi", "jklm"}));
        System.out.println(solution(new String[]{"word", "war", "warrior", "world"}));
    }
}
