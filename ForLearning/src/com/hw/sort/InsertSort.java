package com.hw.sort;

public class InsertSort {
	
	public static int[] insertSort(int[] in){
		
		for(int i=1; i<in.length;i++){
			int temp = in[i];
			for(int j=0; j<i; j++){
				if(in[j] > temp){
					for(int k=i;k>j;k--){
						in[k] = in[k-1];						
					}
					in[j] = temp;
					break;
				}
			}
		}
		return in;
	}
	
	public static void main(String[] args){
		int[] in = {93,1,3,5,6,100,8,45,432,90,2,54,33};
		
		in = insertSort(in);
		
		for(int i=0; i<in.length;i++){
			System.out.println(in[i]);
		}
	}
	
}
