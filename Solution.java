import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * This is the first recursive problems that I found
 * in the LeetCode site.
 * 
 * https://leetcode.com/tag/recursion/
 */


/**
 * Definition for a binary tree node.
 */
class TreeNode {

    // **** members ****
    int val;
    TreeNode left;
    TreeNode right;
    
    // **** constructors ****
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

    @Override
    public String toString() {
        return "val: " + this.val + " left: " + this.left + " right: " + this.right;
    }

}


/**
 * 
 */
public class Solution {


    /**
     * This recursive function inserts nodes in level order into a BT.
     * This method does not support 'null' nodes.
     * 
     * !!!This method is NOT used by this solution !!!
     */
    static TreeNode insertLevelNode(String[] arr, TreeNode root, int i) {
    
        // **** recursion base case ****
        if (i < arr.length) {
            
            // **** allocate element (if needed) ****
            if (!arr[i].equals("null")) {
                TreeNode temp = new TreeNode(Integer.parseInt(arr[i]));
                root = temp;
            } else {
                return null;
            }
    
            // **** insert left child ****
            root.left = insertLevelNode(arr, root.left, 2 * i + 1);
    
            // **** insert right child ****
            root.right = insertLevelNode(arr, root.right, 2 * i + 2);
        }
    
        // **** ****
        return root;
    }


    /**
     * Enumerate which child in the node at the head of the queue 
     * should be updated.
     */
    enum Child {
        LEFT,
        RIGHT
    }


    /**
     * Traverse the specified BT displaying the values in order.
     * This method is used to verify that the BT was properly populated.
     */
    static void inOrder(TreeNode root) {
    
        // **** end condition ****
        if (root == null)
            return;
    
        // **** visit the left sub tree ****
        inOrder(root.left);
    
        // **** display the value of this node ****
        System.out.print(root.val + " ");
    
        // **** visit the right sub tree ****
        inOrder(root.right);
    }


    /**
     * Display the specified BT in level order.
     * This method is used to verify that the BT was properly populated.
     */
    static void levelOrder(TreeNode root) { 
    
        // **** end condition ****
        if (root == null) 
            return; 
    
        Queue<TreeNode> q = new LinkedList<TreeNode>();
    
        // **** to start the process ****
        q.add(root);
        
        // **** loop displaying a tree level ****
        while (!q.isEmpty()) { 
    
            // **** ****
            System.out.print(q.peek().val + " ");
    
            // **** ****
            if (q.peek().left != null) 
                q.add(q.peek().left);
    
            // **** ****
            if (q.peek().right != null) 
                q.add(q.peek().right);
    
            // **** ****
            q.remove();
        }
    } 


    /**
     * This method populates a BT in level order as specified by the array.
     */
    static TreeNode populateTree(String[] arr) {
    
        // **** root for the BT ****
        TreeNode root = null;
    
        // **** ****
        Queue<TreeNode> q = new LinkedList<TreeNode>();
    
        // **** traverse the array of values inserting the nodes into the BT ****
        for (String strVal : arr)
            root = insertValue(root, strVal, q);
    
        // **** clear the queue ****
        q = null;
    
        // **** return the root of the BT ****
        return root;
    }


    // **** child turn to insert on node at head of queue ****
    static Child  insertChild = Child.LEFT;
 

    /**
     * This method inserts the next value into the specified BT.
     * This method is called repeatedly from the populateTree method.
     */
    static TreeNode insertValue(TreeNode root, String strVal, Queue<TreeNode> q) {
    
        // **** node to add to the BT in this pass ****
        TreeNode node = null;
    
        // **** create a node (if needed) ****
        if (!strVal.equals("null"))
            node = new TreeNode(Integer.parseInt(strVal));
    
        // **** BT is empty (this is the root node) ****
        if (root == null)
            root = node;
    
        // **** add node to left child (if possible) ****
        else if (insertChild == Child.LEFT) {
        
            // **** add this node as the left child ****
            if (node != null)
                q.peek().left = node; 
            
            // **** for next pass ****
            insertChild = Child.RIGHT;
        }
    
        // **** add node to right child (if possible) ****
        else if (insertChild == Child.RIGHT) {
        
            // **** add this node as a right child ****
            if (node != null)
                q.peek().right = node;
    
            // **** remove node from queue ****
            q.remove();
    
            // **** for next pass ****
            insertChild = Child.LEFT;
        }
    
        // **** add this node to the queue (if needed) ****
        if (node != null)
            q.add(node);
        
        // **** return the root of the BT ****
        return root;
    }


    // **** must be outside of the recursive method ****
    static int maxPath = 0;
    
    
    /**
     * Given a binary tree, find the length of the longest path where each 
     * node in the path has the same value. This path may or may not pass 
     * through the root.
     */
    static int longestUnivaluePath(TreeNode root) {
    
        // **** initialize value ****
        maxPath = 0;
    
        // **** recursive call ****
        lup(root);
    
        // **** return value ****
        return maxPath;
    }


    /**
     * Given a binary tree, find the length of the longest path where each 
     * node in the path has the same value. This path may or may not pass 
     * through the root.
     * This is a recursive function.
     * Time complexity: O(n)
     */
    static int lup(TreeNode root) {
    
        // **** base case ***
        if (root == null)
            return 0;
    
        // **** process the left tree ****
        int leftLen = lup(root.left);
    
        // **** process the right tree ****
        int rightLen = lup(root.right);
    
        // **** store the maximum child lengths ****
        int leftMax = 0;
        int rightMax = 0;
    
        // **** ****
        if ((root.left != null) &&
            (root.left.val == root.val))
            leftMax += leftLen + 1;
    
        // **** ****
        if ((root.right != null) &&
            (root.right.val == root.val))
            rightMax += rightLen + 1;
    
        // **** update the maxPath ****
        maxPath = Math.max(maxPath, leftMax + rightMax);
    
        // **** return the max length ****
        return Math.max(leftMax, rightMax);
    }


    /**
     * Test scaffolding.
     */
    public static void main(String[] args) {
    
        // **** BT root ****
        TreeNode root = null;
    
        // **** open scanner ****
        Scanner sc = new Scanner(System.in);
        
        // **** read the values for the nodes in the BT ****
        String[] arr = sc.nextLine().split(" ");
    
        // **** populate the BT in level order (supports 'null' values) ****
        root = populateTree(arr);
    
        // ???? level order traversal (check the BT) ????
        System.out.print("main <<< levelOrder: ");
        levelOrder(root);
        System.out.println();
    
        // ???? inorder traversal (check the BT) ????
        System.out.print("main <<<    inOrder: ");
        inOrder(root);
        System.out.println();
    
        // **** close scanner ****
        sc.close();
    
        // **** compute and display the result ****
        System.out.println("main <<< longestUnivaluePath: " + longestUnivaluePath(root));
        }

}