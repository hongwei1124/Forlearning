package com.hw.datastructure;

public class SinglyLinkedList<E> implements Cloneable {
	
	/**
	 * 

    A nested class is a member of its enclosing class. Non-static nested classes (inner classes) 
    have access to other members of the enclosing class, even if they are declared private. Static nested
     classes do not have access to other members of the enclosing class.
    ...

    Note: A static nested class interacts with the instance members of its outer class (and other classes) 
    just like any other top-level class. In effect, a static nested class is behaviorally a top-level
     class that has been nested in another top-level class for packaging convenience.

	 * @author hongwei.li
	 *
	 * @param <E>
	 */
	private static class Node<E>{
		private E element;
		private Node<E> next;
		
		public Node(E e, Node<E> n){
			element = e;
			next = n;
		}
		
		public E getElement(){
			return element;
		}
		
		public Node<E> getNext(){
			return next;
		}
		
		public void setNext(Node<E> n){
			next = n;
		}
	}
	
	private Node<E> head = null;
	private Node<E> tail = null;
	
	private int size = 0;
	
	public SinglyLinkedList(){}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public E first(){
		if(isEmpty()) return null;
		else return head.getElement();
	}
	
	public E last(){
		if(isEmpty()) return null;
		else return tail.getElement();
	}
	
	public void addFirst(E e){
		head = new Node<E>(e, head);
		if(isEmpty()) tail = head;
		size++;
	}
	
	public void addLast(E e){
		Node<E> newest = new Node<E>(e, null);
		if(isEmpty()) head = newest;
		else
			tail.setNext(newest);
		
		tail = newest;
		size++;
	}
	
	public E removeFirst(){
		if(isEmpty()) return null;
		
		E answer = head.getElement();
		head = head.getNext();
		size--;
		if(isEmpty()) tail = null;
		
		return answer;
	}
	
	/**
	 * Just for fun.
	 * @return
	 */
	public E removeLast(){
		
		if(isEmpty()) return null;
		E answer = tail.getElement();
		if(size() == 1) {
			head = null;
			tail = null;
			size--;
			return answer;
		}
		
		Node<E> node = head;
		Node<E> secondLast = null;
		while(node.getNext() != null){
			//next next is null, then it is the secondLast
			if(node.getNext().getNext() == null){
				secondLast = node;
				break;
			}
			
			node = node.getNext();
		}
		
		tail = secondLast;
		tail.setNext(null);
		
		if(isEmpty()) head = tail;
		size--;
		return answer;
		
	}
	
	 @SuppressWarnings({"unchecked"})
	  public boolean equals(Object o) {
	    if (o == null) return false;
	    if (getClass() != o.getClass()) return false;
	    SinglyLinkedList other = (SinglyLinkedList) o;   // use nonparameterized type
	    if (size != other.size) return false;
	    Node walkA = head;                               // traverse the primary list
	    Node walkB = other.head;                         // traverse the secondary list
	    while (walkA != null) {
	      if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
	      walkA = walkA.getNext();
	      walkB = walkB.getNext();
	    }
	    return true;   // if we reach this, everything matched successfully
	  }

	  @SuppressWarnings({"unchecked"})
	  public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
	    // always use inherited Object.clone() to create the initial copy
	    SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
	    if (size > 0) {                    // we need independent chain of nodes
	      other.head = new Node<E>(head.getElement(), null);
	      Node<E> walk = head.getNext();      // walk through remainder of original list
	      Node<E> otherTail = other.head;     // remember most recently created node
	      while (walk != null) {              // make a new node storing same element
	        Node<E> newest = new Node<E>(walk.getElement(), null);
	        otherTail.setNext(newest);     // link previous node to this one
	        otherTail = newest;
	        walk = walk.getNext();
	      }
	    }
	    return other;
	  }

	  public int hashCode() {
	    int h = 0;
	    for (Node walk=head; walk != null; walk = walk.getNext()) {
	      h ^= walk.getElement().hashCode();      // bitwise exclusive-or with element's code
	      h = (h << 5) | (h >>> 27);              // 5-bit cyclic shift of composite code
	    }
	    return h;
	  }

	  /**
	   * Produces a string representation of the contents of the list.
	   * This exists for debugging purposes only.
	   */
	  public String toString() {
	    StringBuilder sb = new StringBuilder("(");
	    Node<E> walk = head;
	    while (walk != null) {
	      sb.append(walk.getElement());
	      if (walk != tail)
	        sb.append(", ");
	      walk = walk.getNext();
	    }
	    sb.append(")");
	    return sb.toString();
	  }
	  
	  public static void main(String[] args){
		  SinglyLinkedList<String> sl = new SinglyLinkedList<String>();
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
