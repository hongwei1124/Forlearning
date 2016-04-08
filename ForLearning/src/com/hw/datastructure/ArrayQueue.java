package com.hw.datastructure;

public class ArrayQueue<E> implements Queue<E> {
	private final int CAPACITY = 1000;
	private int f=0; //the front pointer
	private int sz=0; //size of the elements
	
	private E[] data;
	
	public ArrayQueue(int capacity){
		data = (E[]) new Object[capacity];
	}
	
	public ArrayQueue(){
		data = (E[]) new Object[CAPACITY];
	}
	
	@Override
	public int size() {
		return sz;
	}

	@Override
	public boolean isEmpty() {
		return sz==0;
	}

	@Override
	public void enqueue(E e) throws IllegalStateException{
		if(sz==data.length) throw new IllegalStateException("Queue is full");
		int avail = (f+sz) % data.length;
		data[avail] = e;
		sz++;

	}

	@Override
	public E first() {
		if(isEmpty()) return null;
		return data[f];
	}

	@Override
	public E dequeue() {
		if(isEmpty()) return null;
		E answer = data[f];
		data[f] = null;
		f = (f+1) % data.length;
		sz--;
		return answer;
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder("(");
	    int k = f;
	    for (int j=0; j < sz; j++) {
	      if (j > 0)
	        sb.append(", ");
	      sb.append(data[k]);
	      k = (k + 1) % data.length;
	    }
	    sb.append(")");
	    return sb.toString();
	  }

}
