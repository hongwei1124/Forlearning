package com.hw.datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class SortedTableMap<K, V> extends AbstractSortedMap<K, V> {
	
	private ArrayList<MapEntry<K,V>> table = new ArrayList<MapEntry<K,V>>();
	
	public SortedTableMap() { super(); }
	
	public SortedTableMap(Comparator<K> comp) { super(comp); }
	
	/**
	   * Returns the smallest index for range table[low..high] inclusive
	   * storing an entry with a key greater than or equal to the given k.
	   * If no such element exists, returns index high+1 by convention.
	   * @param key the query key
	   * @param low the lowest index of the relevant table range
	   * @param high the highest index of the relevant table range
	   * @return lowest j such that table[j] has key greater than or equal to given key
	   * (or index high+1 if no such entry exists)
	   */
	
	private int findIndex(K key, int low, int high){
		if(high < low) return high+1;    //no entries found. If empty table, it return 0 as the first place to hold an entry.
		int mid = (low + high) /2;
		int comp = compare(key, table.get(mid));
		if(comp == 0) return mid;
		else if(comp < 0) return findIndex(key, low, mid -1 ); // key is too big, go to left to find
		else return findIndex(key, mid+1, high);
	}
	
	private int findIndex(K key) { return findIndex(key, 0, table.size() - 1); }
	
	@Override
	public Entry<K, V> firstEntry() {
		return safeEntry(0);
	}

	@Override
	public Entry<K, V> lastEntry() {
		return safeEntry(table.size()-1);
	}

	@Override
	public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
		return safeEntry(findIndex(key));
	}

	@Override
	public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
		int j = findIndex(key);
	    if (j == size() || ! key.equals(table.get(j).getKey()))
	      j--;    // look one earlier (unless we had found a perfect match)
	    return safeEntry(j);
	}

	@Override
	public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
		return safeEntry(findIndex(key) - 1);
	}

	@Override
	public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
		int j = findIndex(key);
	    if (j < size() && key.equals(table.get(j).getKey()))
	      j++;    // go past exact match
	    return safeEntry(j);
	}
	
	private Iterable<Entry<K,V>> snapshot(int startIndex, K stop) {
	    ArrayList<Entry<K,V>> buffer = new ArrayList<Entry<K,V>>();
	    int j = startIndex;
	    while (j < table.size() && (stop == null || compare(stop, table.get(j)) > 0))
	      buffer.add(table.get(j++));
	    return buffer;
	  }

	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey)
			throws IllegalArgumentException {
		return snapshot(findIndex(fromKey), toKey);
	}

	@Override
	public int size() {
		return table.size();
	}

	@Override
	public V get(K key) {
		checkKey(key);
		int j = findIndex(key);
		if(j == size() || compare(key, table.get(j)) != 0 ) return null;
		return table.get(j).getValue();
	}

	@Override
	public V put(K key, V value) {
		checkKey(key);
		int j = findIndex(key);
		System.out.println(j);
		if(j < size() && compare(key,table.get(j)) == 0)
			return table.get(j).setValue(value);
		table.add(j, new MapEntry<K,V>(key,value));         //this is sorted, when adding new entry. As the arraylist will push back elements in the arraylist
		return null;
	}

	@Override
	public V remove(K key) {
		checkKey(key);
		int j = findIndex(key);
		if(j == size() || compare(key,table.get(j)) != 0) return null; //no found
		return table.remove(j).getValue();  //remove will shrink the elements to fill the void.
	}
	
	private Entry<K,V> safeEntry(int j) {
	    if (j < 0 || j >= table.size()) return null;
	    return table.get(j);
	  }
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return snapshot(0, null);
	}
	
	public static void main(String[] args){
		SortedTableMap<String, String> sm = new SortedTableMap<String, String>();
		sm.put("ZZ", "ZZ");
		sm.put("DD", "DD");
		sm.put("CC", "CC");
		sm.put("AA", "AA");
		sm.put("BB", "BB");
		sm.put("HH", "HH");
		sm.remove("CC");
		
		Iterator it = (Iterator) sm.keySet().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}

}
