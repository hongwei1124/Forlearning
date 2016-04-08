package com.hw.datastructure;

public class ArrayDeque<E> implements Deque<E> {
	private static final int CAPACITY = 1000;
	private int f = -1;
	private int r = -1;
	private int size = 0;
	private E[] data;
	
	public ArrayDeque(int capacity){
		data = (E[]) new Object[capacity];
	}
	
	public ArrayDeque(){
		this(CAPACITY);
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public E first() {
		if(isEmpty()) return null;
		return data[f];
	}

	@Override
	public E last() {
		if(isEmpty()) return null;
		return data[r];
	}

	@Override
	public void addFirst(E e) throws IllegalStateException{
		if(size == data.length) throw new IllegalStateException("Deque is full");
		if(f > r){
			if(f == data.length-1){
				f = 0;
			}else{
				f = f - 1;
			}
			data[f] = e;
		}else if(f == r){
			if(f == -1){
				f = 0;
				r = 0;
			}else{
				f = f - 1;
			}
			data[f] = e;
			
		}else{
			if(f == 0){
				f = data.length -1;
			}else{
				f = f - 1;
			}
			
			data[f] = e;
		}	
		
		size++;

	}

	@Override
	public void addLast(E e) throws IllegalStateException {
		if(size == data.length) throw new IllegalStateException("Deque is full");
		if(r > f){
			if(r == data.length-1){
				r = 0;
			}else{
				r = r + 1;
			}
			data[r] = e;
		}else if(f == r){
			if(r == -1){
				f = 0;
				r = 0;
			}else{
				r = r + 1;
			}
			data[r] = e;
			
		}else{
			if(r == 0){
				r = data.length -1;
			}else{
				r = r + 1;
			}
			
			data[r] = e;
		}	
		
		size++;

	}

	@Override
	public E removeFirst() {
		if(isEmpty()) return null;
		E answer = data[f];
		data[f] = null;
		f = (f+1) % data.length;
		size--;
		if(isEmpty()){ //if empty, put front and rear at the initial again.
			f = -1;
			r = -1;
		}
		return answer;
	}

	@Override
	public E removeLast() {
		if(isEmpty()) return null;
		E answer = data[r];
		data[r] = null;
		r = (r-1) % data.length;
		size--;
		if(isEmpty()){ //if empty, put front and rear at the beginning again.
			f = -1;
			r = -1;
		}
		return answer;
	}

}
