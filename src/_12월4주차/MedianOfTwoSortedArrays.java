package _12월4주차;

import java.util.*;

public class MedianOfTwoSortedArrays {
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[] A = {2, 4};
        int[] B = {6, 8, 10, 12, 14};
        findMedianSortedArrays(A, B);
    }
}
/*
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
*/