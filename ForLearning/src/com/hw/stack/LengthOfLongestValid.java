package com.hw.stack;

import java.util.Stack;

/**
 * Given a string consisting of opening and closing parenthesis, find length of the longest valid parenthesis substring.

Examples:

Input : ((()
Output : 2
Explanation : ()

Input: )()())
Output : 4
Explanation: ()() 

Input:  ()(()))))
Output: 6
Explanation:  ()(()))

 * @author hongwei.li
 *
 */

public class LengthOfLongestValid {
	
	public int longestValidSubString(String input){
		
		int result = 0;
		Stack<Integer> s = new Stack<Integer>();
		s.push(-1);
		for(int i=0;i<input.length();i++){
			char a = input.charAt(i);
			if('(' == a) {
				s.push(i);
			}else{
				s.pop();
				if(!s.isEmpty())
					result = max(result, i-s.peek());
				else
					s.push(i);
			}
		}
		return result;
		
	}
	
	private int max(int a, int b){
		if(a>b) return a;
		else return b;
	}
	
	public static void main(String[] args){
		
		String str = "((()()";
		
		LengthOfLongestValid lv = new LengthOfLongestValid();
		System.out.println(lv.longestValidSubString(str));
		
		str = "()(()))()()()()";
		System.out.println(lv.longestValidSubString(str));
		
	}

}
