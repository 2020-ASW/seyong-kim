package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 숨바꼭질5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int old = Integer.parseInt(input[0]);
        int young = Integer.parseInt(input[1]);

        if (old == young) {
            System.out.println(0);
            return;
        }

        int[] youngPos = new int[1001];
        youngPos[0] = young;
        for (int t = 1; t < youngPos.length; t++) youngPos[t] = youngPos[t - 1] + t;

        System.out.println(bfs(youngPos, old));
    }

    private static int bfs(int[] youngPos, int subinPosition) {
        Queue<Subin> queue = new LinkedList<>();
        queue.add(new Subin(subinPosition, 0));

        int[][] visited = new int[2][500001];
        Arrays.fill(visited[0], -1);
        Arrays.fill(visited[1], -1);

        String[] operations = new String[]{"+", "-", "*"};
        while (!queue.isEmpty()) {
            Subin subin = queue.poll();

            if (youngPos[subin.time] > 500000) break;

            if (visited[(subin.time) % 2][youngPos[subin.time]] != -1) return subin.time;

            for (String op : operations) {
                int nPos = operate(op, subin.location);
                int nTime = (subin.time + 1) % 2;

                if (nPos > 500000 || nPos < 0) continue;

                if (visited[nTime][nPos] == -1) {
                    visited[nTime][nPos] = subin.time + 1;
                    queue.add(new Subin(nPos, subin.time + 1));
                }
            }
        }
        return -1;
    }

    private static int operate(String op, int val) {
        if ("+".equals(op)) return val + 1;
        else if ("-".equals(op)) return val - 1;
        else return val * 2;
    }

    static class Subin {
        int location, time;

        Subin(int location, int time) {
            this.location = location;
            this.time = time;
        }
    }
}
