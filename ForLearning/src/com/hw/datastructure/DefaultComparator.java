package com.hw.datastructure;

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E> {

	@Override
	public int compare(E o1, E o2) throws ClassCastException{
		System.out.println("Object 1:"+o1+"  Object 2:"+ o2+"   Result:"+((Comparable<E>) o1).compareTo(o2));
		return ((Comparable<E>) o1).compareTo(o2);
	}

}
