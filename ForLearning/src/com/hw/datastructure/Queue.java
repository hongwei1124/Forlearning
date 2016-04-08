package com.hw.datastructure;

public interface Queue<E> {
	
	int size();

	 
	  boolean isEmpty();

	 
	  void enqueue(E e);

	  
	  E first();

	 
	  E dequeue();

}
