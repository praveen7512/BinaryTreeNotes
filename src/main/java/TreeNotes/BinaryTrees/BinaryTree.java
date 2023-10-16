package TreeNotes.BinaryTrees;

import jdk.jshell.JShell;

import java.util.*;

public class BinaryTree {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    static class Pair {
        Node node;
        int level;

        Pair(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(10);
        Node node2 = new Node(11);
        Node node3 = new Node(12);
        Node node4 = new Node(12);
        Node node5 = new Node(12);

        node1.left = node2;
        node1.right = node3;
        node2.right = node5;
        node2.left = node4;

        //  10
        //   /  \
        //  11   12
        // / \
        //12  12

//        List<List<Integer>> list = new ArrayList<>();
////        averageLevel(node1, list).forEach(System.out::println);
//
//        List<Integer> list1 = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
//        list1.add(3);
//
//        list2.add(9);
//        list2.add(20);
//
//        list.add(list1);
//        list.add(list2);
//
//        listToAverage(list);


        topViewOfBinaryTree(node1);

    }


    public static void printTreeDfsInorder(Node root) {
        if (root == null) return;
        printTreeDfsInorder(root.left);
        System.out.println(root.data);
        printTreeDfsInorder(root.right);

    }

    public static int heightOfBinaryTree(Node root) {
        if (root == null) return 0;

        return Math.max(heightOfBinaryTree(root.left), heightOfBinaryTree(root.right)) + 1;
    }

    public static void printKthNode(Node root, int k) {

        if (root == null) return;
        if (k == 0) {
            System.out.println(root.data);
        }

        printKthNode(root.left, k - 1);
        printKthNode(root.right, k - 1);

        //never to use k++ and k-- in the function arguments

    }

    public static void printUsingBFS(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print(curr.data + " ");
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }

    }

    public static List<Integer> leftViewOfTree(Node root) {

        List<Integer> list = new ArrayList<>();
        helperLeftView(root, 0, list);
        return list;

    }

    private static void helperLeftView(Node root, int level, List list) {
        if (root == null) return;

        if (list.size() == level) list.add(root.data);
        helperLeftView(root.left, level + 1, list);
        helperLeftView(root.right, level + 1, list);
    }

    private static int diameterOfTree(Node root) {

        if (root == null) return 0;

        int option1 = heightOfBinaryTree(root.right) + heightOfBinaryTree(root.left);
        int option2 = diameterOfTree(root.right);
        int option3 = diameterOfTree(root.left);

        int maxi = Math.max(option2, option3);
        int ans = Math.max(maxi, option1);
        return ans;
    }

    private static void boundaryTraversal(Node root) {

        if (root == null) return;


    }

    private static List<List<Integer>> bfsTraversel(Node root, List<List<Integer>> ans) {
        if (root == null) return ans;

        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            int level = queue.size();
            List<Integer> list = new ArrayList<>(level);


            for (int i = 0; i < level; i++) {
                Node curr = queue.poll();
                list.add(curr.data);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            ans.add(list);
        }
        return ans;


    }

    private static List<List<Integer>> averageLevel(Node root, List<List<Integer>> ans) {
        if (root == null) return ans;

        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            int levels = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < levels; i++) {
                Node curr = queue.poll();
                list.add(curr.data);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            ans.add(list);


        }
        return ans;
    }

    private static List<Double> listToAverage(List<List<Integer>> ans) {
        List<Double> list = new ArrayList<>();

        for (List<Integer> an : ans) {
            double sum = 0;

            for (Integer integer : an) {
                sum = sum + integer;
            }

            sum = ((double) sum / an.size());
            list.add((double) sum);
        }
        return list;
    }

    private static void flattenBinaryTree(Node root) {

        //o(n) space lete hai yeh solution better hai o(1) mein krke dikhao
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        helperFlatter(root, queue);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            curr.left = curr.right = null;
            if (!queue.isEmpty()) curr.right = queue.peek();
        }

    }

    private static void helperFlatter(Node root, Queue<Node> queue) {

        if (root == null) return;
        queue.offer(root);
        helperFlatter(root.left, queue);
        helperFlatter(root.right, queue);
    }

    private static void topViewOfBinaryTree(Node root) {

        if (root == null) {
            return;
        }

        Queue<NodeWithLevel> queue = new LinkedList();
        HashMap<Integer, Node> hm = new LinkedHashMap<>();
        queue.offer(new NodeWithLevel(root, 0));

        while (!queue.isEmpty()) {

            NodeWithLevel curr = queue.poll();
            int curr_level = curr.level;

            if (!hm.containsKey(curr.level)) {
                hm.put(curr.level, curr.node);

            }

            if (curr.node.left != null) queue.add(new NodeWithLevel(curr.node.left, curr_level - 1));
            if (curr.node.right != null) queue.add(new NodeWithLevel(curr.node.right, curr_level + 1));

        }

        for (Node i : hm.values()) {
            System.out.printf(i.data + " ");
        }

    }




}








