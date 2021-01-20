package _1월4주차;

class BinaryTreeMaximumPathSum {
    static int max;

    public static int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;

        getPathSum(root);

        return max;
    }

    private static int getPathSum(TreeNode node) {
        if (node == null) return 0;

        int leftValue = Math.max(0, getPathSum(node.left));
        int rightValue = Math.max(0, getPathSum(node.right));

        max = Math.max(max, leftValue + node.val + rightValue);

        // System.out.print(node.val + "] "+leftValue + " / " + rightValue + "\n");

        return Math.max(leftValue + node.val, node.val + rightValue);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(-1, null, null);
        maxPathSum(root);
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
