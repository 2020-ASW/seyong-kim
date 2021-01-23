package _1월4주차;

import java.util.ArrayList;
import java.util.List;

public class 무지의먹방라이브 {
    public int solution(int[] food_times, long k) {
        int answer = 0;

        int length = food_times.length;

        List<Food> list = new ArrayList<>();
        for (int i = 0; i < food_times.length; i++) list.add(new Food(i + 1, food_times[i]));



        return answer;
    }

    static class Food {
        int no;
        int amount;

        Food(int no, int amount) {
            this.no = no;
            this.amount = amount;
        }
    }
}
