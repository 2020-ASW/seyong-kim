package _12월3주차;

public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int ans = 0;

        int leftIndex = 0;
        int rightIndex = height.length - 1;

        while (leftIndex < rightIndex) {
            ans = Math.max(ans, calMax(height, leftIndex, rightIndex));
            if (height[leftIndex] < height[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }

        return ans;
    }

    private static int calMax(int[] height, int leftIndex, int rightIndex) {
        int minHeight = Math.min(height[leftIndex], height[rightIndex]);
        int distance = rightIndex - leftIndex;

        return minHeight * distance;
    }

    public static void main(String[] args) {
        int[] height;

        height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
        height = new int[]{1, 1};
        System.out.println(maxArea(height));
        height = new int[]{4, 3, 2, 1, 4};
        System.out.println(maxArea(height));
        height = new int[]{1, 2, 1};
        System.out.println(maxArea(height));
        height = new int[]{2, 3, 10, 5, 7, 8, 9};
        System.out.println(maxArea(height));

    }
}
