package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HashMap<K, V> {
	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>();
		System.out.println("test put/get/remove");
		for (int i = 0; i < 10; i++) {
			map.put("" + i, i);
		}
		for (int i = 0; i < 10; i++) {
			System.out.printf("key_%d=%d, %b\n", i, map.get("" + i), map.get("" + i) == i);
		}
		map.remove("3");
		System.out.println("test remove 3");
		System.out.println("test HashMap.entrySet");
		for (HashMap.Entry<String, Integer> e : map.entrySet()) {
			System.out.printf("%s=%d\n", e.getKey(), e.getValue());
		}
		System.out.println("test rehash");
		for (int i = 10; i < 20; i++) {
			map.put("" + i, i);
		}
		for (HashMap.Entry<String, Integer> e : map.entrySet()) {
			System.out.printf("%s=%d\n", e.getKey(), e.getValue());
		}
	}

	public static class Entry<K, V> {
		final K key; // >< note "final" here
		V val;
		Entry<K, V> next;

		public Entry(K key, V val, Entry<K, V> next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
		
		public final K getKey() { // >< note "final" here
			return key;
		}
		
		public final V getValue() {
			return val;
		}
		
		// >< which 3 API entry offer ?
		public void setValue(V value) {
			this.val = value;
		}
		
	}

	private static final int DEFAULT_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private Entry<K, V>[] buckets;
	private float loadFactor; // >< determine when to rehash
	private int size; // >< how many key-value pairs are acutally stored in the HashMap
	private Set<K> keySet;
	private Collection<V> values;
	private Set<Entry<K, V>> entrySet;

	@SuppressWarnings("unchecked")
	public HashMap(int capacity, float loadFactor) {
		buckets = (Entry<K, V>[])new Entry[capacity]; //>< note type transformation here
		this.loadFactor = loadFactor;
		keySet = new HashSet<>();
		values = new ArrayList<>();
		entrySet = new HashSet<>();
	}

	public HashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public V put(K key, V val) {
		// check if reach loadFactor threshold
		if (needRehashing()) {
			rehashing();
		}
		int idx = getIndex(key, buckets.length);
		// search key
		Entry<K, V> curr = buckets[idx];
		while (curr != null && !equalsKey(curr.key, key)) {
			curr = curr.next;
		}
		if (curr == null) { // didn't find key, insert a new Entry
			size++;
			Entry<K, V> newEntry = new Entry<K, V>(key, val, buckets[idx]);
			buckets[idx] = newEntry;

			// update keySet, values, entrySet
			keySet.add(key);
			values.add(val);
			entrySet.add(newEntry);
			return null;
		} else { // find key, update Entry
			V oldValue = curr.val;
			curr.val = val;

			// update values
			values.remove(oldValue);
			values.add(val);
			return oldValue;
		}
	}

	public V get(K key) {
		int idx = getIndex(key, buckets.length);
		Entry<K, V> curr = buckets[idx];
		while (curr != null && !equalsKey(curr.key, key)) {
			curr = curr.next;
		}
		return curr != null ? curr.val : null;
	}

	public boolean containsKey(K key) {
		return get(key) != null;
	}

	public boolean containsValue(V value) {
		// special case
		if (isEmpty()) {
			return false;
		}
		for (Entry<K, V> entry : buckets) { // >< use iterator as much as possible
			while (entry != null) {
				if (equalsValue(entry.val, value)) {
					return true;
				}
				entry = entry.next;
			}
		}
		
//		for (int i = 0; i < buckets.length; i++) {
//			Entry curr = buckets[i];
//			while (curr != null && curr.val != value) {
//				curr = curr.next;
//			}
//			if (curr != null && curr.val == value) {
//				return true;
//			}
//		}
		return false;
	}

	public V remove(K key) {
		int idx = getIndex(key, buckets.length);
		Entry<K, V> prev = null;
		Entry<K, V> curr = buckets[idx];
		while (curr != null && !equalsKey(curr.key, key)) {
			prev = curr;
			curr = curr.next;
		}
		if (curr == null) {
			return null;
		} else {
			size--;
			// update keySet, values, entrySet
			keySet.remove(key);
			values.remove(curr.val);
			entrySet.remove(curr);

			V res = curr.val;
			if (prev == null) {
				buckets[idx] = curr.next;
			} else {
				prev.next = curr.next;
			}
			return res;
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
//		for (int i = 0; i < buckets.length; i++) {
//			buckets[i] = null;
//		}
		Arrays.fill(buckets, null); // >< more simple
		size = 0; // >< do not forget
	}

	public Set<K> keySet() {
		return keySet;
	}

	public Collection<V> values() {
		return values;
	}

	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}

	private int getIndex(K key, int n) {
		if (key == null) {
			return 0;
		}
		int idx = key.hashCode() % n;
		return idx < 0 ? idx + n : idx;
	}
	
	private int hash(K key) {
		if (key == null) {
			return 0;
		}
//		return key.hashCode() & 0x7FFFFFFF; // >< guarantee non-negative
		return (key.hashCode() ^ (key.hashCode() >>> 16)) & 0x7FFFFFFF;
	}
	
	// HashMap in Java ensures tables size is always 2's power
	// so it can use hash(key) & (n -1) to get the module
	private int getIndex2(K key, int n) {
		return hash(key) % n;
	}

	// Time: O(max(s.length(), t.length()))
	private boolean equalsKey(K s, K t) {
		if (s == null && t == null) {
			return true;
		} else if (s == null || t == null) {
			return false;
		}
		return s.hashCode() == t.hashCode() && s.equals(t);
	}
	
	private boolean equalsValue(V v1, V v2) {
		if (v1 == null && v2 == null) {
			return true;
		} else if (v1 == null || v2 == null) {
			return false;
		}
		return v1.equals(v2);
		// another way
		// return v1 == v2 || v1 != null && v1.equals(v2);
	}
	
	// split checkReHash() into needRehashing() and rehashing()
	private boolean needRehashing() {
		return size * 1.0 / buckets.length >= loadFactor;
	}
	
	@SuppressWarnings("unchecked")
	private boolean rehashing() {
		System.out.println("rehash happen @size=" + size);
		int newLen = buckets.length * 2;
		Entry<K, V>[] newBuckets = (Entry<K, V>[])(new Entry[newLen]);
		for (Entry<K, V> e : entrySet) {
			int idx = getIndex(e.key, newLen);
			e.next = newBuckets[idx];
			newBuckets[idx] = e;
		}
		buckets = newBuckets;
		return true;
	}
	
	
	private void checkRehash() {
		if (size * 1.0 / buckets.length < loadFactor) {
			return;
		}
		System.out.println("rehash happen @size=" + size);
		int newLen = buckets.length * 2;
		Entry<K, V>[] newBuckets = (Entry<K, V>[])(new Entry[newLen]);
		for (Entry<K, V> e : entrySet) {
			int idx = getIndex(e.key, newLen);
			e.next = newBuckets[idx];
			newBuckets[idx] = e;
		}
		buckets = newBuckets;
	}
}
