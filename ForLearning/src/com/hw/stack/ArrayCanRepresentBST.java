package com.hw.stack;

import java.util.Stack;

/**
 * Given an array of numbers, return true if given array can represent preorder traversal of a Binary Search Tree, 
 * else return false. Expected time complexity is O(n).
 * 
Input:  pre[] = {2, 4, 3}
Output: true
Given array can represent preorder traversal
of below tree
    2
     \
      4
     /
    3

Input:  pre[] = {2, 4, 1}
Output: false
Given array cannot represent preorder traversal
of a Binary Search Tree.

Input:  pre[] = {40, 30, 35, 80, 100}
Output: true
Given array can represent preorder traversal
of below tree
     40
   /   \
 30    80 
  \      \
  35     100 


Input:  pre[] = {40, 30, 35, 20, 80, 100}
Output: false
Given array cannot represent preorder traversal
of a Binary Search Tree.
 * 
 * 
 * @author hongwei.li
 *
 */

public class ArrayCanRepresentBST {
	
	boolean canRepresentBST(int pre[], int n) {
        // Create an empty stack
        Stack<Integer> s = new Stack<Integer>();
 
        // Initialize current root as minimum possible
        // value
        int root = Integer.MIN_VALUE;
 
        // Traverse given array
        for (int i = 0; i < n; i++) {
            // If we find a node who is on right side
            // and smaller than root, return false
            if (pre[i] < root) {
                return false;
            }
 
            // If pre[i] is in right subtree of stack top,
            // Keep removing items smaller than pre[i]
            // and make the last removed item as new
            // root.
            while (!s.empty() && s.peek() < pre[i]) {
                root = s.peek();
                s.pop();
                System.out.println("Pop from s:"+pre[i]);
            }
 
            // At this point either stack is empty or
            // pre[i] is smaller than root, push pre[i]
            s.push(pre[i]);
            System.out.println("Push to s:"+pre[i]);
        }
        return true;
    }
 
    public static void main(String args[]) {
    	ArrayCanRepresentBST bst = new ArrayCanRepresentBST();
        int[] pre1 = new int[]{40, 30, 35, 80, 100};
        int n = pre1.length;
        if (bst.canRepresentBST(pre1, n) == true) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        int[] pre2 = new int[]{40, 30, 20, 35, 34, 80, 100};
        int n1 = pre2.length;
        if (bst.canRepresentBST(pre2, n) == true) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
 
//This code is contributed by Mayank Jaiswal


