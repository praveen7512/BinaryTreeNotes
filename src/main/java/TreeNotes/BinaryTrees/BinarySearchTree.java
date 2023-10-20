package TreeNotes.BinaryTrees;

public class BinarySearchTree {

    static class TreeNode {

        int val;
        TreeNode right;
        TreeNode left;


        TreeNode(int val) {
            this.val = val;
            right = left = null;
        }

    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        //     4
        //    / \
        //   2   6
        //  / \ / \
        // 1  3 5  7
        insertInBST(root, 8);
        searchInBST(root,8);

    }


    public static void searchInBST(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        if (root.val == target) {
            System.out.println("the target has been found -> " + target);
        }

        if (root.val > target) searchInBST(root.left, target);
        if (root.val < target) searchInBST(root.right, target);

    }

    public static TreeNode insertInBST(TreeNode root, int elememt) {
        if (root == null) {
            return new TreeNode(elememt);
        }
        if (root.val > elememt) root.left = insertInBST(root.left, elememt);
        if (root.val < elememt) root.right = insertInBST(root.right, elememt);

        return root;
    }


    public static void printBST(TreeNode root) {
        if (root == null) {
            return;
        }
        printBST(root.left);
        System.out.print(root.val + " ");
        printBST(root.right);
    }
}
