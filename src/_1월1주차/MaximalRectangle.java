package _1월1주차;

import java.util.Stack;

/*
시간 복잡도 : O(n*m)
1. 배열의 row[0]는 arr에 그대로 copy
2. maxArea에 arr에서 얻을 수있는 최대 넓이를 저장
3. row[1]를 봤을때
    if row[1][i] == 1 : arr[i] += 1
    else row[1][i] == 0 : arr[i] = 0
4. arr에서 얻을 수있는 최대 넓이를 구한 뒤 갱신
5. 3~4번 row 끝날 때까지 반복

참고
- https://www.youtube.com/watch?v=g8bSdXCG-lA&ab_channel=TusharRoy-CodingMadeSimple
- https://leetcode.com/problems/maximal-rectangle/discuss/29055/My-java-solution-based-on-Maximum-Rectangle-in-Histogram-with-explanation
* */

public class MaximalRectangle {
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int[] arr = new int[matrix[0].length];

        for (int i = 0; i < matrix[0].length; i++) {
            arr[i] = charToInt(matrix[0][i]);
        }
        int result = getLargestInLine(arr);

        for (int row = 1; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (charToInt(matrix[row][col]) == 1)
                    arr[col] += 1;
                else
                    arr[col] = 0;
            }
            result = Math.max(result, getLargestInLine(arr));
        }
        return result;
    }

    public static int getLargestInLine(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= height.length; i++) {
            int h = (i == height.length ? -1 : height[i]);

            if (stack.isEmpty() || h >= height[stack.peek()])
                stack.push(i);
            else {
                int highestPosition = stack.pop();
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, height[highestPosition] * width);
                i--;
            }
        }
        return maxArea;
    }

    private static int charToInt(char ch) {
        return ch - '0';
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        matrix = new char[][]{{'1'}};
        System.out.println(maximalRectangle(matrix));
    }
}