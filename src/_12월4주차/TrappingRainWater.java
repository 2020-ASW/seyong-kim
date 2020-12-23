package _12월4주차;

public class TrappingRainWater {
    public static int trap(int[] height) {

        if (height.length == 0) return 0;

        int[] leftH = new int[height.length];
        int[] rightH = new int[height.length];

        leftH[0] = height[0];
        rightH[height.length - 1] = height[height.length - 1];

        int leftIndex = 1, rightIndex = height.length - 2;
        int maxHeight = 0;

        while (leftIndex < height.length) {
            leftH[leftIndex] = Math.max(leftH[leftIndex - 1], height[leftIndex]);
            rightH[rightIndex] = Math.max(rightH[rightIndex + 1], height[rightIndex]);

            maxHeight = Math.max(maxHeight, height[leftIndex]);

            leftIndex++;
            rightIndex--;
        }
//        System.out.println(Arrays.toString(leftH));
//        System.out.println(Arrays.toString(rightH));

        int answer = 0;

        for (int i = 0; i < height.length; i++) {
            answer += Math.min(leftH[i], rightH[i]) - height[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] h = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(h));
        h = new int[]{2, 0, 0, 2};
        System.out.println(trap(h));
    }
}
