package com.hw.stack;


import java.util.Stack;

/**
 * Given an expression with only ‘}’ and ‘{‘. 
 * The expression may not be balanced. Find minimum number of bracket reversals to make the expression balanced.
 * 
 * 
Input:  exp = "}{"
Output: 2
We need to change '}' to '{' and '{' to
'}' so that the expression becomes balanced, 
the balanced expression is '{}'

Input:  exp = "{{{"
Output: Can't be made balanced using reversals

Input:  exp = "{{{{"
Output: 2 

Input:  exp = "{{{{}}"
Output: 1 

Input:  exp = "}{{}}{{{"
Output: 3

 * @author hongwei.li
 *
 */

public class BracketReversals {
	
	  public static int minReversals(char exp[])
	  {
	    int leftcurls = 0;
	    int rightcurls = 0;
	    Stack<Character> stack = new Stack<Character>();
	    for (int i = 0;i < exp.length;i++){
	    	
	    	if(stack.isEmpty()){
	    		stack.push(exp[i]);
	    		
	    		if('{' == exp[i]){
		    		leftcurls++;
		    	}else if('}' == exp[i]){
		    		rightcurls++;
		    	}
	    		
	    		continue;
	    	}else{
	    		
	    		if('{' == exp[i] && '}' == stack.peek()){
	    			stack.pop();
		    		rightcurls--;
		    	}else if('}' == exp[i] && '{' == stack.peek()){
		    		stack.pop();
		    		leftcurls--;
		    	}else if('{' == exp[i]){
		    		stack.push(exp[i]);
		    		leftcurls++;
		    	}else if('}' == exp[i]){
		    		stack.push(exp[i]);
		    		rightcurls++;
		    	}
	    		
	    	}
	    		    	
	    }
	    System.out.println("Mod is: "+(rightcurls+leftcurls) % 2);
	    if((rightcurls+leftcurls) % 2 == 1) return -1;
	    	
	    return (leftcurls + rightcurls)/2;
	    	
	   }  	
	    	
	  public static void main(String[] args)
	  {
	    String str = "{{{}}}{";
	    System.out.println(minReversals(str.toCharArray()));
	 
	  }
	    

}
