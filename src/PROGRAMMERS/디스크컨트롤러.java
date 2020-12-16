package PROGRAMMERS;

import java.util.*;

class 디스크컨트롤러 {
    public int solution(int[][] jobs) {
        LinkedList<Job> waitQueue = new LinkedList<>();

        PriorityQueue<Job> controller = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.workingTime - o2.workingTime;
            }
        });

        for (int[] job : jobs) {
            waitQueue.offer(new Job(job[0], job[1]));
        }
        waitQueue.sort(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.requestTime - o2.requestTime;
            }
        });

        int time = waitQueue.peekFirst().requestTime;
        int result = 0;
        int numberOfJob = 0;
        while (numberOfJob < jobs.length) {
            while (!waitQueue.isEmpty() && waitQueue.peek().requestTime <= time) {
                controller.offer(waitQueue.pollFirst());
            }

            if (!controller.isEmpty()) {
                Job job = controller.poll();
                time += job.workingTime;
                result += time - job.requestTime;
                numberOfJob++;
            } else {
                time++;
            }
        }
        return result / numberOfJob;
    }

    static class Job {
        int requestTime;
        int workingTime;

        Job(int requestTime, int workingTime) {
            this.requestTime = requestTime;
            this.workingTime = workingTime;
        }

        @Override
        public String toString() {
            return requestTime + "ms 시점에 " + workingTime + "ms 걸리는 작업";
        }
    }
}