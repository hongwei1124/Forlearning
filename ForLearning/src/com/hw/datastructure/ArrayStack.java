package com.hw.datastructure;

public class ArrayStack<E> implements Stack<E> {
	
	private final int CAPACITY = 1000;
	private E[] data;
	private int index = 0;
	
	public ArrayStack(int capacity){
		data = (E[]) new Object[capacity];
		
	}
	
	public ArrayStack(){
		data = (E[]) new Object[CAPACITY];
		
	}

	@Override
	public int size() {
		return data.length;
	}

	@Override
	public boolean isEmpty() {
		return data.length==0;
	}

	@Override
	public void push(E e) throws IllegalStateException {
		if(size() == data.length) throw new IllegalStateException("Stack is full");
		data[++index] = e;
		
	}

	@Override
	public E top() {
		if(isEmpty()) return null;
		return data[index];
	}

	@Override
	public E pop() {
		if(isEmpty()) return null;
		E answer = data[index];
		data[index] = null;
		index--;
		return answer;
	}

}
