package _1월4주차;

import java.util.*;

public class CouplesHoldingHands {

    public int minSwapsCouples(int[] row) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            map.put(row[i], i);
        }

        int swaping = 0;

        for (int i = 0; i < row.length; i += 2) {
            // 짝꿍 위치
            int index;

            if (row[i] % 2 == 0) {
                if (row[i + 1] == row[i] + 1) continue;

                index = map.get(row[i] + 1);

            } else {
                if (row[i + 1] == row[i] - 1) continue;

                index = map.get(row[i] - 1);

            }

            int tmp = row[i + 1];
            row[i + 1] = row[index];
            row[index] = tmp;

            map.put(tmp, index);
            map.put(row[i + 1], i + 1);

            swaping++;
        }
        return swaping;
    }
}
