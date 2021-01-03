package _1월1주차;

public class RecoverBinarySearchTree {
    static private TreeNode p;
    static private TreeNode q;
    static private TreeNode prev;

    public static void recoverTree(TreeNode root) {
        prev = null;
        p = null;
        q = null;

        discover(root);

        int tmp = p.val;
        p.val = q.val;
        q.val = tmp;
    }

    private static void discover(TreeNode node) {
        if (node == null) return;

        discover(node.left);

        if (prev != null && prev.val >= node.val) {
            p = p == null ? prev : p;
            q = node;
        }
        prev = node;

        discover(node.right);
    }

    public static void main(String[] args) {
        // [3,1,4,null,null,2]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);    root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        recoverTree(root);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() { }

        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
