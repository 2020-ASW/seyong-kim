package _1월2주차;

import java.util.HashMap;
import java.util.Map;

public class RangeSumQuery {
    static class NumArray {
        int[] origin;
        int[] sumArr;
        Map<Integer, Integer> diffRecord;

        public NumArray(int[] nums) {
            origin = nums.clone();
            sumArr = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    sumArr[i] = nums[i];
                    continue;
                }
                sumArr[i] = sumArr[i - 1] + nums[i];
            }

            diffRecord = new HashMap<>();
        }

        public void update(int idx, int val) {
            int diff = val - origin[idx];

            if (diffRecord.containsKey(idx)) {
                int newDiff = diffRecord.get(idx) + diff;

                if (newDiff == 0)
                    diffRecord.remove(idx);
                else
                    diffRecord.put(idx, newDiff);

            } else {
                diffRecord.put(idx, diff);
            }
            origin[idx] = val;
        }

        public int sumRange(int i, int j) {
            int sum;
            if (i == 0)
                sum = sumArr[j];
            else
                sum = sumArr[j] - sumArr[i - 1];

            int diffSum = 0;
            for (int x : diffRecord.keySet()) {
                if (i <= x && x <= j) {
                    diffSum += diffRecord.get(x);
                }
            }
            return sum + diffSum;
        }
    }

    public static void main(String[] args) {
        NumArray nums = new NumArray(new int[]{1, 3, 5});

        System.out.println(nums.sumRange(0, 2));
        nums.update(1, 2);
        System.out.println(nums.sumRange(0, 2));
    }
}
