package com.hw.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E> {
	
	private static class Node<E> implements Position<E> {
		
		private E element;
		private Node<E> prev, next;

		@Override
		public E getElement() throws IllegalStateException {
			
			return element;
		}
		
		public Node(E e, Node<E> p, Node<E> n){
			element = e;
			prev = p;
			next = n;
		}
		
		public Node<E> getNext(){
			return next;
		}
		
		public Node<E> getPrev(){
			return prev;
		}
		
		public void setNext(Node<E> n){
			next = n;
		}
		
		public void setPrev(Node<E> p){
			prev = p;
		}
		
		public void setElement(E e){
			element = e;
		}
		
	}
	
	private int size = 0;
	private Node<E> header = null;
	private Node<E> trailer = null;
	
	public LinkedPositionalList() {
	    header = new Node<E>(null, null, null);      // create header
	    trailer = new Node<E>(null, header, null);   // trailer is preceded by header
	    header.setNext(trailer);                    // header is followed by trailer
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
	public Position<E> first() {
		if(isEmpty()) return null;
		return header.getNext();
	}

	@Override
	public Position<E> last() {
		if(isEmpty()) return null;
		return trailer.getPrev();
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getPrev();
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getNext();
	}

	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
	    Node<E> newest = new Node<E>(e, pred, succ);  // create and link a new node
	    pred.setNext(newest);
	    succ.setPrev(newest);
	    size++;
	    return newest;
	  }
	
	@Override
	public Position<E> addFirst(E e) {
		
		return addBetween(e,header,header.getNext());
	}

	@Override
	public Position<E> addLast(E e) {
		return addBetween(e,trailer.getPrev(),trailer);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e,node.getPrev(),node);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e,node,node.getNext());
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> prev = node.getPrev();
		Node<E> next = node.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		size--;
		
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		
		return answer;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
	    if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
	    Node<E> node = (Node<E>) p;     // safe cast
	    if (node.getNext() == null)     // convention for defunct node
	      throw new IllegalArgumentException("p is no longer in the list");
	    return node;
	}
	
	private class PositionIterator implements Iterator<Position<E>> {

	    /** A Position of the containing list, initialized to the first position. */
	    private Position<E> cursor = first();   // position of the next element to report
	    /** A Position of the most recent element reported (if any). */
	    private Position<E> recent = null;       // position of last reported element

	    /**
	     * Tests whether the iterator has a next object.
	     * @return true if there are further objects, false otherwise
	     */
	    public boolean hasNext() { return (cursor != null);  }

	    /**
	     * Returns the next position in the iterator.
	     *
	     * @return next position
	     * @throws NoSuchElementException if there are no further elements
	     */
	    public Position<E> next() throws NoSuchElementException {
	      if (cursor == null) throw new NoSuchElementException("nothing left");
	      recent = cursor;           // element at this position might later be removed
	      cursor = after(cursor);
	      return recent;
	    }

	    /**
	     * Removes the element returned by most recent call to next.
	     * @throws IllegalStateException if next has not yet been called
	     * @throws IllegalStateException if remove was already called since recent next
	     */
	    public void remove() throws IllegalStateException {
	      if (recent == null) throw new IllegalStateException("nothing to remove");
	      LinkedPositionalList.this.remove(recent);         // remove from outer list
	      recent = null;               // do not allow remove again until next is called
	    }
	  }
	
	private class PositionIterable implements Iterable<Position<E>> {
	    public Iterator<Position<E>> iterator() { return new PositionIterator(); }
	}
	
	/**
	 * adapter pattern. As we only need to get E element.
	 * @author hongwei.li
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    Iterator<Position<E>> posIterator = new PositionIterator();
	    public boolean hasNext() { return posIterator.hasNext(); }
	    public E next() { return posIterator.next().getElement(); } // return element!
	    public void remove() { posIterator.remove(); }
	 }

}
