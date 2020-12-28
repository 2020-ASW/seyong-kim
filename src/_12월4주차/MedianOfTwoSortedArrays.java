package _12월4주차;

import java.util.*;

public class MedianOfTwoSortedArrays {
    static private final int INFINITY = Integer.MAX_VALUE;

    /**
     * Time complexity : O(log(m+n))
     * */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n)
            return findMedianSortedArrays(nums2, nums1);

        double median = 0.0;

        // always m <= n
        int start = 0, end = m, halfLen = (m + n + 1) / 2;
        while (start <= end) {
            int partitionX = (start + end) / 2;
            int partitionY = halfLen - partitionX;

            int maxLeftX = (partitionX == 0) ? -INFINITY : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? INFINITY : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? -INFINITY : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? INFINITY : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((m + n) % 2 != 0) {
                    median = Math.max(maxLeftX, maxLeftY);
                } else {
                    median = (double)(Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                }
                break;
            } else if (maxLeftX > minRightY) {
                end = partitionX - 1;
            } else {    //maxLeftY > minRightX
                start = partitionX + 1;
            }
        }
        return median;
    }

    /**
     * Time complexity : O(m+n)
     * */
    public double Legacy__FindMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> nums3 = new ArrayList<>();

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                nums3.add(nums1[i++]);
            } else {
                nums3.add(nums2[j++]);
            }
        }

        while (i < nums1.length)
            nums3.add(nums1[i++]);

        while (j < nums2.length)
            nums3.add(nums2[j++]);

        if (nums3.size() % 2 == 0) {
            return (double) (nums3.get(nums3.size() / 2) + nums3.get((nums3.size() / 2 - 1))) / 2;
        } else {
            return nums3.get(nums3.size() / 2);
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 4};
        int[] B = {6, 8, 10, 12, 14};
        findMedianSortedArrays(A, B);
    }
}