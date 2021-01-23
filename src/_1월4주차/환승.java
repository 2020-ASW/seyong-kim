package _1월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 환승 {
    static int N, K, M, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 역의 개수
        K = Integer.parseInt(st.nextToken());   // 하이퍼튜브가 연결하는 역의 개수
        M = Integer.parseInt(st.nextToken());   // 하이퍼튜브의 개수

        ArrayList<Node>[] stationGraph = new ArrayList[M + 1];
        ArrayList<Node>[] hyperTubeGraph = new ArrayList[N + 1];
        for (int i = 0; i < stationGraph.length; i++) stationGraph[i] = new ArrayList<>();
        for (int i = 0; i < hyperTubeGraph.length; i++) hyperTubeGraph[i] = new ArrayList<>();


        for (int hyperTube = 1; hyperTube <= M; hyperTube++) {
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < K; i++) {
                int stationNum = Integer.parseInt(st.nextToken());
                stationGraph[hyperTube].add(new Station(stationNum, 0));
                hyperTubeGraph[stationNum].add(new HyperTube(hyperTube));
            }
        }

        answer = Integer.MAX_VALUE;

        bfs(stationGraph, hyperTubeGraph, new boolean[N + 1], new boolean[M + 1]);

        System.out.println(answer+1);
    }

    private static void bfs(ArrayList<Node>[] stationGraph, ArrayList<Node>[] hyperTubeGraph, boolean[] stnVisited, boolean[] hypVisited) {
        Queue<Station> queue = new LinkedList<>();
        queue.add(new Station(1, 0));
        stnVisited[1] = true;

        while (!queue.isEmpty()) {
            Station now = queue.poll();

            if (now.getNo() == N) {
                answer = Math.min(answer, now.getDistance());
                break;
            }
            for (Node nextHyperTube : hyperTubeGraph[now.getNo()]) {
                if (hypVisited[nextHyperTube.getNo()]) continue;

                for (Node nextStation : stationGraph[nextHyperTube.getNo()]) {
                    if (stnVisited[nextStation.getNo()]) continue;

                    queue.add(new Station(nextStation.getNo(), now.getDistance() + 1));
                    stnVisited[nextStation.getNo()] = true;
                    hypVisited[nextHyperTube.getNo()] = true;
                }
            }
        }
    }

    interface Node {
        int getNo();

        int getDistance();

        void plusDistance();
    }

    static class Station implements Node {
        int no;
        int distance;

        Station(int no, int distance) {
            this.no = no;
            this.distance = distance;
        }

        @Override
        public int getNo() {
            return this.no;
        }

        @Override
        public int getDistance() {
            return this.distance;
        }

        @Override
        public void plusDistance() {
            this.distance += 1;
        }
    }

    static class HyperTube implements Node {
        int no;

        HyperTube(int no) {
            this.no = no;
        }

        @Override
        public int getNo() {
            return this.no;
        }

        @Override
        public int getDistance() {
            return 0;
        }

        @Override
        public void plusDistance() {

        }
    }
}
