package LEETCODE;

import java.util.*;

public class FourSum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> ansList = new ArrayList<>();

        int n1, n2;

        for (int i = 0; i < nums.length; i++) {     // 첫번째 숫자 선택 Loop

            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            n1 = nums[i];

            for (int j = i + 1; j < nums.length; j++) { // 두번째 숫자 선택 Loop

                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }

                n2 = nums[j];

                int start = j + 1;
                int end = nums.length - 1;

                while (start < end) {
                    int n3 = nums[start];       // 세번째 숫자
                    int n4 = nums[end];         // 네번째 숫자

                    int sum = n1 + n2 + n3 + n4;

                    if (sum == target) {
                        List<Integer> ans = new ArrayList<>();
                        ans.add(n1);
                        ans.add(n2);
                        ans.add(n3);
                        ans.add(n4);

                        ansList.add(ans);

                        // 이전에 골랐던 숫자를 고르면 답이 중복되므로 다른 숫자가 나올때까지 좁혀준다
                        start++;
                        while (start < end && nums[start - 1] == nums[start])
                            start++;
                        end--;
                        while (start < end && nums[end + 1] == nums[end])
                            end--;

                        continue;
                    }


                    if (sum < target) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return ansList;
    }
}
