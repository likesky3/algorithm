package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator implements Iterator<String>{

	private int iter;
	private String[] data;
	public ArrayIterator(String[] input) throws Exception {
		if (input == null) {
			throw new Exception("It's nonsense to create an iterator for null,"
					+ " you wuold get NullPointerException if you use it later.");
		}
		data = input;
		iter = -1;
	}
	
	@Override
	public boolean hasNext() {
		return iter + 1 < data.length;
	}
	
	@Override
	public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException(); // >< usually iterator use NoSuchElementException
//			return null; // or throw OutOfBoundException
		}
		return data[++iter];
	}
}
