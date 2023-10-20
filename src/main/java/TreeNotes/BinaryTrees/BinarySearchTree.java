package TreeNotes.BinaryTrees;

public class BinarySearchTree {

    static class TreeNode{

         int val;
         TreeNode right;
         TreeNode left;


        TreeNode(int val) {
            this.val=val;
            right=left=null;
        }

    }


    public static void main(String[] args) {

    }


    public static void searchInBST(TreeNode root,int target){
        if (root == null) {
            return;
        }
        if (root.val==target) {
            System.out.println(" " + target);
        }

        if(root.val>target) searchInBST(root.right,target);
        if(root.val<target) searchInBST(root.left,target);

    }
}
