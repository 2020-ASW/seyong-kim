package _1월3주차;

public class RecoverBinarySearchTree {
    private TreeNode prev;
    private TreeNode node1;
    private TreeNode node2;

    public void recoverTree(TreeNode root) {
        prev = null;
        node1 = null;
        node2 = null;

        discover(root);

        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }

    private void discover(TreeNode node) {
        if (node == null) return;

        discover(node.left);

        if (prev != null && prev.val > node.val) {
            if(node1 == null) node1 = prev;
            node2 = node;
        }
        prev = node;

        discover(node.right);
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
