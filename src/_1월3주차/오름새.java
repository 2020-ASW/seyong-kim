package _1월3주차;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 오름새 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNext()) {
            int day = sc.nextInt();
            int[] stocks = new int[day];

            for (int i = 0; i < day; i++) stocks[i] = sc.nextInt();

            List<Integer> list = new ArrayList<>();

            for (int stock : stocks) {
                if (list.size() == 0 || list.get(list.size() - 1) < stock) {
                    list.add(stock);
                    continue;
                }
                int l = 0;
                int r = list.size() - 1;

                while (l <= r) {
                    int mid = (l + r) / 2;

                    if (list.get(mid) < stock) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                list.set(l, stock);
            }
            sb.append(list.size()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
