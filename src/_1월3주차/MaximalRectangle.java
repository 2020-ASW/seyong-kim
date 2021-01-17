package _1월3주차;

import java.util.Stack;

public class MaximalRectangle {
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int[] line = new int[matrix[0].length];

        for (int i = 0; i < matrix[0].length; i++) line[i] = matrix[0][i] - '0';

        int answer = getLargestInLine(line);

        for (int row = 1; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                line[col] = matrix[row][col] - '0' == 0 ? 0 : line[col] + 1;
            }
            answer = Math.max(answer, getLargestInLine(line));
        }

        return answer;
    }

    private static int getLargestInLine(int[] line) {
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        for (int i = 0; i <= line.length; i++) {
            int height = (i != line.length ? line[i] : -1);

            if (stack.isEmpty() || height >= line[stack.peek()]) {
                stack.push(i);
                continue;
            }
            int heightestIdx = stack.pop();
            int width = stack.isEmpty() ? i : (i - stack.peek() - 1);

            maxArea = Math.max(maxArea, line[heightestIdx] * width);
            i--;
        }

        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
//        matrix = new char[][]{{'1'}};
        System.out.println(maximalRectangle(matrix));
    }
}
