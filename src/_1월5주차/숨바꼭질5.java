package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 숨바꼭질5 {
    static int old, young;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        old = Integer.parseInt(input[0]);
        young = Integer.parseInt(input[1]);

        if (old == young) {
            System.out.println(0);
            return;
        }

        int[] youngPos = new int[1000];
        youngPos[0] = young;
        for (int t = 1; t < youngPos.length; t++) youngPos[t] = youngPos[t - 1] + t;

        System.out.println(bfs(youngPos, old));
    }

    private static int bfs(int[] youngPos, int subinPosition) {
        Queue<Subin> queue = new LinkedList<>();
        queue.add(new Subin(subinPosition, 0));

        while (!queue.isEmpty()) {
            Subin now = queue.poll();

            for (String op : new String[]{"+", "-", "*"}) {
                int np = operate(op, now.location);

                if (np > 500000 || np < 0) continue;

                if (youngPos[now.time + 1] == np) {
                    return now.time + 1;
                }

                queue.add(new Subin(np, now.time + 1));
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
