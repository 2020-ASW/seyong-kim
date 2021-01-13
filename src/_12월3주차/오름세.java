package _12월3주차;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 오름세 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int day = sc.nextInt();
            int[] stocks = new int[day];
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < day; i++) {
                stocks[i] = sc.nextInt();
            }

            for (int num : stocks) {
                // 리스트가 비어있거나, 제일 끝값보다 num이 큰 경우
                if (list.size() == 0 || num > list.get(list.size() - 1)) {
                    list.add(num);
                    continue;
                }

                // 리스트의 제일 끝 값보다 num이 작은 경우
                // 이분 탐색을 통해 적정 위치를 찾는다
                // 찾은 후 적정 위치의 값을 현재 num로 교체한다
                int l = 0, r = list.size() - 1;
                while (l < r) {
                    int mid = (l + r) / 2;

                    if(list.get(mid) < num)
                        l = mid+1;
                    else
                        r = mid;
                }
                list.set(r, num);
            }
            sb.append(list.size()+"\n");
        }
        System.out.println(sb.toString());
    }
}
