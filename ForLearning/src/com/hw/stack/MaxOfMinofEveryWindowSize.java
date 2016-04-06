package com.hw.stack;

import java.util.Stack;


/**
 * 
 * Given an integer array of size n, find the maximum of the minimum’s of every window size in the array. 
 * Note that window size varies from 1 to n.

Example:

Input:  arr[] = {10, 20, 30, 50, 10, 70, 30}
Output:         70, 30, 20, 10, 10, 10, 10

First element in output indicates maximum of minimums of all 
windows of size 1.
Minimums of windows of size 1 are {10}, {20}, {30}, {50}, {10},
{70} and {30}.  Maximum of these minimums is 70

Second element in output indicates maximum of minimums of all 
windows of size 2.
Minimums of windows of size 2 are {10}, {20}, {30}, {10}, {10},
and {30}.  Maximum of these minimums is 30

Third element in output indicates maximum of minimums of all 
windows of size 3.
Minimums of windows of size 3 are {10}, {20}, {10}, {10} and {10}. 
Maximum of these minimums is 20

Similarly other elements of output are computed.
 * @author hongwei.li
 *
 */

public class MaxOfMinofEveryWindowSize {
	
	public int MaxofMinofAWindow(int[] arr, int n){
		int size = 0;
		int index = 0;
		int max = 0;
		for(int i = 0; (i+n-1) < arr.length; i++){
			int min = arr[i];
			for(int j = i; j < i+n; j++){
					min = min(min, arr[j]);
			}
			
			max = max(max, min);
			size++;
		}
		
		return max;
		
	}
	
	public void printAll(int[] arr){
		for(int i =1; i < arr.length;i++){
			System.out.println(MaxofMinofAWindow(arr,i));
		}
	}
	
	private int min(int a, int b){
		if(a<b) return a;
		return b;
	}
	
	private int max(int a, int b){
		if(a<b) return b;
		return a;
	}
	
	public static void main(String[] args){
		
		int arr[] = {10, 20, 30, 50, 10, 70, 30,80};
		
		MaxOfMinofEveryWindowSize mw = new MaxOfMinofEveryWindowSize();
		mw.printAll(arr);
		
	}

}
