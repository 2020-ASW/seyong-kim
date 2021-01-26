package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 괄호추가하기 {
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        ArrayList<Character> operations = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                operations.add(ch);
                continue;
            }
            nums.add(ch - '0');
        }

        answer = Integer.MIN_VALUE;
        dfs(nums, operations, nums.get(0), 0);
        System.out.println(answer);
    }

    private static void dfs(ArrayList<Integer> nums, ArrayList<Character> operations, int result, int opIdx) {
        if (opIdx >= operations.size()) {
            answer = Math.max(answer, result);
            return;
        }

        int noBracketCalc = calculate(result, nums.get(opIdx + 1), operations.get(opIdx));
        dfs(nums, operations, noBracketCalc, opIdx + 1);

        if (operations.size() > opIdx + 1) {
            int bracketCalc = calculate(nums.get(opIdx + 1), nums.get(opIdx + 2), operations.get(opIdx + 1));
            dfs(nums, operations, calculate(result, bracketCalc, operations.get(opIdx)), opIdx + 2);
        }
    }

    private static int calculate(int n1, int n2, char op) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            default:
                return -1;
        }
    }
}
