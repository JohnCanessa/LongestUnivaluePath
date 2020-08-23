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
}


/**
 * 
 */
public class Solution {

    /**
     * Test scaffolding.
     */
    public static void main(String[] args) {

        // **** root for binary tree ****
        TreeNode root = null;

        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read line of values ****
        String[] strVal = sc.nextLine().split(" ");

        // **** ****
        for (String str : strVal) {

            // **** ****
            

        }




        // **** close scanner ****
        sc.close();


        // **** ****
        
    }
}