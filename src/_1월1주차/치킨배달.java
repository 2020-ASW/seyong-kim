package _1월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class 치킨배달 {
    static int N, M, result;
    static int[][] map;
    static List<int[]> houseList;
    static List<int[]> chickenHouseList;
    static List<int[]> selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        houseList = new ArrayList<>();
        chickenHouseList = new ArrayList<>();
        selected = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    houseList.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    chickenHouseList.add(new int[]{i, j});
                }
            }
        }
        result = Integer.MAX_VALUE;

        selectChickenHouse(0, 0);

        System.out.println(result);
    }



    private static void selectChickenHouse(int start, int cnt) {
        if (cnt == M) {
            // 도시의 치킨 거리 중 최소값을 구한다
            int distance = calculate();
            result = Math.min(result, distance);
            return;
        }

        for (int i = start; i < chickenHouseList.size(); i++) {
            selected.add(chickenHouseList.get(i));
            selectChickenHouse(i + 1, cnt + 1);
            selected.remove(selected.size()-1);
        }
    }

    // 집을 기준으로 선택된 M개의 치킨집과의 거리를 구한 뒤 모두 더해 도시의 치킨 거리를 구한다
    private static int calculate() {
        // 도시의 치킨 거리
        int sum = 0;
        for (int[] house : houseList) {
            int min = Integer.MAX_VALUE;

            for (int[] chickenHouse : selected) {
                int distance = getDistance(house[0], house[1], chickenHouse[0], chickenHouse[1]);
                min = Math.min(min, distance);
            }

            sum += min;
        }

        return sum;
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
