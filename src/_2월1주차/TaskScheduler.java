package _2월1주차;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {
    public static int leastInterval(char[] tasks, int n) {

        int[] frequency = new int[26];
        for (char c : tasks) frequency[c - 'A']++;

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int f : frequency)
            if (f != 0) pq.add(f);

        // <time, frequency>
        Map<Integer, Integer> schedule = new HashMap<>();

        int time = 0, tasksRemaining = tasks.length;

        while (tasksRemaining > 0) {
            Integer coolTimeTask = schedule.get(time);
            if (coolTimeTask != null)
                pq.add(coolTimeTask);

            Integer cur = pq.poll();
            if (cur != null) {
                tasksRemaining--;
                cur--;
                if (cur > 0)
                    schedule.put(time + n + 1, cur);
            }
            time++;
        }

        return time;
    }

    public static void main(String[] args) {
        leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
    }
}
