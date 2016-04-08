package com.hw.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K,V> extends AbstractMap<K, V> {
	
	 private ArrayList<MapEntry<K,V>> table = new ArrayList<MapEntry<K,V>>();
	 
	 public UnsortedTableMap() { }

	@Override
	public int size() {
		return table.size();
	}
	
	private int findIndex(K key) {
	    int n = table.size();
	    for (int j=0; j < n; j++)
	      if (table.get(j).getKey().equals(key))
	        return j;
	    return -1;                                   // special value denotes that key was not found
	  }

	@Override
	public V get(K key) {
		int j = findIndex(key);
	    if (j == -1) return null;                         // not found
	    return table.get(j).getValue();
	}

	@Override
	public V put(K key, V value) {
		int j = findIndex(key);
	    if (j == -1) {
	      table.add(new MapEntry<K,V>(key, value));          // add new entry
	      return null;
	    } else                                            // key already exists
	      return table.get(j).setValue(value);            // replaced value is returned
	}

	@Override
	public V remove(K key) {
		int j = findIndex(key);
	    int n = size();
	    if (j == -1) return null;                         // not found
	    V answer = table.get(j).getValue();
	    if (j != n - 1)
	      table.set(j, table.get(n-1));                   // relocate last entry to 'hole' created by removal
	    table.remove(n-1);                                // remove last entry of table
	    return answer;
	}
	
	private class EntryIterator implements Iterator<Entry<K,V>> {
	    private int j=0;
	    public boolean hasNext() { return j < table.size(); }
	    public Entry<K,V> next() {
	      if (j == table.size()) throw new NoSuchElementException("No further entries");
	      return table.get(j++);
	    }
	    public void remove() { throw new UnsupportedOperationException("remove not supported"); }
	  } //----------- end of nested EntryIterator class -----------

	  //---------------- nested EntryIterable class ----------------
	private class EntryIterable implements Iterable<Entry<K,V>> {
	    public Iterator<Entry<K,V>> iterator() { return new EntryIterator(); }
	  } //----------- end of nested EntryIterable class -----------

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}

}
