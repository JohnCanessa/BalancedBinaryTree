import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Represents a node in the binary tree.
 */
class TreeNode {

    // **** class members ****
    int val;
    TreeNode left;
    TreeNode right;

    // **** constructor(s) ****
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

    // **** ****
    @Override
    public String toString() {
        return "" + this.val;
    }
}


/**
 * LeetCode 110. Balanced Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree {


    /**
     * Populate binary tree using values from a first breadth search traversal.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static TreeNode populateTree(Integer[] arr) {

        // **** sanity checks ****
        if (arr == null || arr.length == 0) {
            return null;
        }

        // **** initialization ****
        Queue<TreeNode> treeNodeQ = new LinkedList<>();
        Queue<Integer> integerQ = new LinkedList<>();

        // **** populate integer queue ****
        for (int i = 1; i < arr.length; i++) {
            integerQ.offer(arr[i]);
        }

        // **** prime tree node queue ****
        TreeNode treeNode = new TreeNode(arr[0]);
        treeNodeQ.offer(treeNode);

        // **** ****
        while (!integerQ.isEmpty()) {

            // **** ****
            Integer leftVal = integerQ.isEmpty() ? null : integerQ.poll();
            Integer rightVal = integerQ.isEmpty() ? null : integerQ.poll();
            TreeNode current = treeNodeQ.poll();

            // **** ****
            if (leftVal != null) {
                TreeNode left = new TreeNode(leftVal);
                current.left = left;
                treeNodeQ.offer(left);
            }

            // **** ****
            if (rightVal != null) {
                TreeNode right = new TreeNode(rightVal);
                current.right = right;
                treeNodeQ.offer(right);
            }
        }

        // **** return binary tree ****
        return treeNode;
    }


    /**
     * Display node values in a binary tree in order traversal. 
     * Recursive approach.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.toString() + " ");
            inOrder(root.right);
        }
    }


    /**
     * Compute depth of binary tree.
     * Utility function.
     * Recursive call.
     */
    static int depth(TreeNode root) { 

        // **** base case ****
        if (root == null) 
            return 0; 

        // **** recursive call ****
        return 1 + Math.max(depth(root.left), depth(root.right)); 
    }


    /**
     * Given a binary tree, determine if it is height-balanced.
     * Recursive call.
     * 
     * Runtime: 1 ms, faster than 46.61% of Java online submissions.
     * Memory Usage: 41.9 MB, less than 5.01% of Java online submissions.
     */
    static boolean isBalanced(TreeNode root) {

        // **** base condition ****
        if (root == null)
            return true;

        // **** get the depth of the left and right sub trees ****
        int leftH   = depth(root.left);
        int rightH  = depth(root.right);

        // ???? ????
        System.out.println("<<< root: " + root.val + " leftH: " + leftH 
                            + " rightH: " + rightH);

        // **** recursive calls ****
        if (Math.abs(leftH - rightH) <= 1 
            && isBalanced(root.left)
            && isBalanced(root.right))
            return true;
        else
            return false;
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read binary tree dfs data ****
        String[] strArr = br.readLine().trim().split(",");

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<<  strArr: " + Arrays.toString(strArr));

        // **** create and populate Integer array ****
        Integer[] arr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("null"))
                arr[i] = null;
            else
                arr[i] = Integer.parseInt(strArr[i]);
        }

        // ???? ????
        System.out.println("main <<<     arr: " + Arrays.toString(arr));

        // **** create and populate binary tree ****
        TreeNode root = populateTree(arr);

        // ???? ????
        System.out.print("main <<< inOrder: ");
        inOrder(root);
        System.out.println();

        // **** compute and display if binary tree is balanced ****
        System.out.println("main <<<  output: " + isBalanced(root));
    }
}