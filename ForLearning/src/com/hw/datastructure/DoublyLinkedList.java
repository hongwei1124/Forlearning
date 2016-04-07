package com.hw.datastructure;

public class DoublyLinkedList<E> {
	
	private static class Node<E>{
		private E element;
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E e, Node<E> p, Node<E> n){
			element = e;
			prev = p;
			next = n;
		}
		public E getElement(){
			return element;
		}
		
		public Node<E> getPrev(){
			return prev;
		}
		
		public void setPrev(Node<E> p){
			prev = p;
		}
		
		public Node<E> getNext(){
			return next;
		}
		
		public void setNext(Node<E> n){
			next = n;
		}
	}
	
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;
	
	public DoublyLinkedList(){
		header = new Node<E>(null,null,null);
		trailer = new Node<E>(null, header, null);
		header.setNext(trailer);
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public E first(){
		if(isEmpty()) return null;
		return header.getNext().getElement();
	}
	
	public E last(){
		if(isEmpty()) return null;
		
		return trailer.getPrev().getElement();
	}
	
	public void addFirst(E e) {
	    addBetween(e, header, header.getNext());    // place just after the header
	}
	
	public void addLast(E e) {
	    addBetween(e, trailer.getPrev(), trailer);    // place just before the trailer
	}
	
	public E removeFirst(){
		if(isEmpty()) return null;
		return remove(header.getNext());
	}
	
	public E removeLast(){
		if(isEmpty()) return null;
		return remove(trailer.getPrev());
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder("(");
	    Node<E> walk = header.getNext();
	    while (walk != trailer) {
	      sb.append(walk.getElement());
	      walk = walk.getNext();
	      if (walk != trailer)
	        sb.append(", ");
	    }
	    sb.append(")");
	    return sb.toString();
	  }
	
	private void addBetween(E e, Node<E> prev, Node<E> next ){
		
		Node<E> newnode = new Node<E>(e,prev,next);
		prev.setNext(newnode);
		next.setPrev(newnode);
		size++;
		
		
	}
	
	private E remove(Node<E> node){
		Node<E> p = node.getPrev();
		Node<E> n = node.getNext();
		
		p.setNext(n);
		n.setPrev(p);
		
		size--;
		
		return node.getElement();
	}
	
	  public static void main(String[] args){
		  DoublyLinkedList<String> sl = new DoublyLinkedList<String>();
		  sl.addFirst("a");
		  System.out.println(sl.removeLast());
		  
		  sl.addFirst("a");
		  sl.addFirst("b");
		  sl.addFirst("c");
		  System.out.println(sl.removeLast());
		  System.out.println(sl.removeLast());
		  System.out.println(sl.removeLast());
		  System.out.println(sl.removeLast());
		  
		  sl.addFirst("a");
		  sl.addFirst("b");
		  sl.addFirst("c");
		  System.out.println(sl.removeFirst());
		  System.out.println(sl.removeFirst());
		  System.out.println(sl.removeFirst());
		  System.out.println(sl.removeFirst());
		  
		  sl.addLast("a");
		  sl.addLast("b");
		  sl.addLast("c");
		  System.out.println(sl.removeFirst());
		  System.out.println(sl.removeFirst());
		  System.out.println(sl.removeFirst());
		  System.out.println(sl.removeFirst());
		  
	  }

}
