package _12월4주차;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    static List<String> answer;
    static char[][] phone;

    public static List<String> letterCombinations(String digits) {
        answer = new ArrayList<>();

        if(digits.length() == 0) return answer;

        phone = new char[10][4];
        phone[2] = new char[]{'a', 'b', 'c'};
        phone[3] = new char[]{'d', 'e', 'f'};
        phone[4] = new char[]{'g', 'h', 'i'};
        phone[5] = new char[]{'j', 'k', 'l'};
        phone[6] = new char[]{'m', 'n', 'o'};
        phone[7] = new char[]{'p', 'q', 'r', 's'};
        phone[8] = new char[]{'t', 'u', 'v'};
        phone[9] = new char[]{'w', 'x', 'y', 'z'};

        dfs(0, digits, new StringBuilder());

        return answer;
    }

    public static void dfs(int idx, String digits, StringBuilder sb) {
        if (idx >= digits.length()) {
            answer.add(sb.toString());
            return;
        }
        int number = Integer.parseInt(digits.charAt(idx) + "");
        for (int i = 0; i < phone[number].length; i++) {
            sb.append(phone[number][i]);
            dfs(idx + 1, digits, sb);
            sb.deleteCharAt(sb.toString().length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
