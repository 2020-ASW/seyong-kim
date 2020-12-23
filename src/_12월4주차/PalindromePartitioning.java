package _12월4주차;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    static List<List<String>> result;

    public static List<List<String>> partition(String s) {
        result = new ArrayList<>();

        dfs(0, new ArrayList<>(), s);

        return result;
    }

    private static void dfs(int start, List<String> list, String s) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String forwardString = s.substring(start, i + 1);

            if (!isPalindrome(forwardString)) continue;

            list.add(forwardString);
            dfs(i + 1, list, s);
            list.remove(list.size() - 1);
        }
    }

    private static boolean isPalindrome(String forward) {
        String backward = new StringBuilder(forward).reverse().toString();
        return forward.equals(backward);
    }

    public static void main(String[] args) {
        String str = "aab";
        System.out.println(partition(str));
        str = "a";
        System.out.println(partition(str));
        str = "rotator";
        System.out.println(partition(str));
    }
}
