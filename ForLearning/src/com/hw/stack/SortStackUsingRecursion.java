package com.hw.stack;

import java.util.Stack;

/**
 * Given a stack, sort it using recursion. Use of any loop constructs like while, for..etc is not allowed. 
 * We can only use the following ADT functions on Stack S:

is_empty(S)  : Tests whether stack is empty or not.
push(S)	     : Adds new element to the stack.
pop(S)	     : Removes top element from the stack.
top(S)	     : Returns value of the top element. Note that this
               function does not remove element from the stack.
 * @author hongwei.li
 *
 */

public class SortStackUsingRecursion {
	
	String indend = " ";
	public void sortStack(Stack<Integer> s){
		indend = indend + "  ";
		if(!s.isEmpty()){
			int temp = s.pop();
			System.out.println(indend + "pop:"+temp);
			sortStack(s);
			indend = indend.substring(0,indend.length()-2);
			sortedInsert(s,temp);
		}
	}
	
	private void sortedInsert(Stack<Integer> s, int temp){
		if(s.isEmpty() || temp > s.peek()){
			s.push(temp);
			System.out.println(indend + "push:"+temp);
		}else{
			int another = s.pop();
			System.out.println(indend + "pop:"+another);
			sortedInsert(s,temp);
			s.push(another);
			System.out.println(indend + "push:"+another);
		}
	}
	
	public static void main(String[] args){
		Stack<Integer> s = new Stack<Integer> ();
		s.push(-3);
		s.push(14);
		s.push(18);
		s.push(-5);
		s.push(30);
		s.push(12);
		
		SortStackUsingRecursion sr = new SortStackUsingRecursion();
		
		sr.sortStack(s);
		
		while(!s.isEmpty()){
			System.out.println(s.pop());
		}
	}

}
