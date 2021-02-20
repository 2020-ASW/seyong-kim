package _2월3주차;

import java.io.*;

/**
 마피아가 아닐것 같은 사람 = "No마피아"
 <br>
 마피아는 서로를 지목하지 않기 때문에
 - 마피아가 지목한 사람은 No마피아일 가능성이 크고, No마피아가 지목한 사람은 마피아일 가능성이 크다
  - No마피아가 지목한 사람이 지목을 한번 당했다면, 그사람은 마피아로 친다(최대 마피아를 구하기 위해)
  -             ''             여러번 지목 당했다면, No 마피아일 가능성이 크다 (마피아가 아니라고 가정하고 재 탐색 필요)

 **/

public class 마피아게임 {
    static int[] arr, cntArr;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // arr[i] = j (i가 j를 지목)
        arr = new int[N + 1];
        // cntArr[i] = j (i가 j번 지목 당함)
        cntArr = new int[N + 1];
        visited = new boolean[N + 1];
        answer = 0;

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            cntArr[arr[i]]++;
        }

        for (int i = 1; i <= N; i++) {
            if (cntArr[i] == 0) {
                dfs(i, true);
            }
        }
        for (int i = 1; i <= N; i++) dfs(i, false);

        bw.write(answer + "\n");
        bw.flush();
    }

    private static void dfs(int n, boolean isMafia) {
        if (visited[n]) return;

        visited[n] = true;

        if (isMafia) answer++;

        int next = arr[n];

        cntArr[next]--;
        if (cntArr[next] == 0 || isMafia) {
            dfs(next, !isMafia);
        }
    }
}
