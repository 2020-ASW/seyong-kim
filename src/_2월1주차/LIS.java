package _2월1주차;

import java.util.ArrayList;
import java.util.List;

public class LIS {
    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            if (list.isEmpty() || num > list.get(list.size() - 1)) {
                list.add(num);
                continue;
            }
            int l = 0, r = list.size() - 1;
            while (l <= r) {
                int mid = (l + r) / 2;

                if (list.get(mid) < num) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            list.set(l, num);
        }
        return list.size();
    }
}
