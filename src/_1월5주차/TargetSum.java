package _1월5주차;

public class TargetSum {
    int answer;

    public int findTargetSumWays(int[] nums, int S) {
        answer = 0;
        dfs(nums, 0, 0, S);
        return answer;
    }

    private void dfs(int[] nums, int idx, int sum, int S) {
        if (idx == nums.length) {
            if (sum == S) answer++;
            return;
        }

        dfs(nums, idx + 1, sum + nums[idx], S);
        dfs(nums, idx + 1, sum - nums[idx], S);
    }
}
