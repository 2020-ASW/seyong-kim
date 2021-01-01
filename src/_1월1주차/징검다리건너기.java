package _1월1주차;

public class 징검다리건너기 {
    public static int solution(int[] stones, int k) {
        int l = 0, r = 200000000;

        while (l <= r) {
            int m = (l + r) / 2;  // 밟을 수 있는 최대값 = 건널수 있는 최소 m-1명

            if (canGo(stones, m, k)) {  // m번 밟을 수 있는지 = m번째 사람까지 건널수 있는지
                // canGo == true 라는 소리는
                // m 값이 넉넉하다는 뜻 = 더 건널 수 있다는 뜻
                // m 값을 조금 더 높게 잡아보자
                l = m + 1;
            } else {
                // canGo == false 라는 소리는
                // m 값이 너무 크다는 뜻 = m명 만큼 건널수 있을 것이라 생각 했지만 아니라는 뜻
                r = m - 1;
            }
        }

        return r;
    }

    /**
     * 돌이 m 만큼 밟힐 것이라고 가정
     * if stone >= m : m번 밟혀도 괜찮음 -> m + 1번도 밟힐 수 있음 -> m명은 지나갈 수 있음
     * else         : m번 이상은 밟을 수 없음, 그러나 K 길이 만큼 점프해서 지나갈 수 있으니 count++
     * */
    private static boolean canGo(int[] stones, int m, int k) {
        int count = 1;  // K길이 만큼 뛰어 넘는지 카운트하는 변수
        for (int stone : stones) {
            if (stone >= m) {   // m보다 크거나 같으면 m번째 까지는 건널 수 있음
                count = 1;
            } else {            // m보다 작으면 이미 0이기 때문에 한 칸 뛰어 넘는걸로 판단
                count++;
                if (count > k)  // K길이 까지 뛸 수 있는데 count가 더 크면 못 뛰어 넘음
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }
}
