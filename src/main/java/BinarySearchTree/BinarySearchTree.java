package BinarySearchTree;

public class BinarySearchTree {

    static int count;

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
        System.out.println(inorderSuccesor(root, 1));

    }


    public static void printBST(TreeNode root) {
        if (root == null) {
            return;
        }
        printBST(root.left);
        System.out.print(root.val + " ");
        printBST(root.right);
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


    public static int floorInBST(TreeNode root, int element) {

        if (root == null) return -1;
        int ans = -1;
        while (root != null) {
            if (root.val == element) return element;
            if (root.val > element) root = root.left;
            if (root.val < element) {
                ans = root.val;
                root = root.right;
            }
        }
        return ans;
    }

    public static boolean isBST(TreeNode root, int min, int max) {

        if (root == null) return true;

        if (root.val < min || root.val > max) return false;

        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);


    }

    public static int kthSmallestInBST(TreeNode root, int k) {
        if (root == null || k == 0) return -1;

        int left = kthSmallestInBST(root.left, k);
        if (left != -1) {
            return left;
        }
        count++;

        if (k == count) return root.val;

        int right = kthSmallestInBST(root.right, k);

        return right;

    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        if (left == null && right != null) return right;
        if (left != null && right == null) return left;
        else return null;


    }

    public static int inorderSuccesor(TreeNode root, int element) {

        if (root == null) {
            return -1;
        }

        int left = inorderSuccesor(root.left, element);
        if (element < root.val) return root.val;
        int right = inorderSuccesor(root.right, element);

        if(left!=-1) return left;
        else return right;


    }
}
