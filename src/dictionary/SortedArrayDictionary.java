package dictionary;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedArrayDictionary <K extends Comparable <? super K> , V> 
              implements DictionaryInterface <K, V> {
	
	private Entry <K, V>[] entries;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 20;
    
    @SuppressWarnings ("unchecked")
    public SortedArrayDictionary(int capacity){
        numberOfEntries = 0;  
        entries = (Entry<K, V>[]) new Entry [capacity];
    }

    public SortedArrayDictionary() { 
        this (DEFAULT_CAPACITY);
    }

	public static void main(String[] args) {
		SortedArrayDictionary <String, Name>  roster = new SortedArrayDictionary <> ();
		roster.add("IC 000012", new Name ("John Adams") );
		roster.add("IC 001111" , new Name ("Niels Bohr"));
		roster.add("IC 000314" , new Name ("Janos Bolyai"));
		roster.add("IC 001234" , new Name ("Steve Jobs"));
		roster.add("IC 000035" , new Name ("Robert Kennedy"));
		roster.add("IC 000259" , new Name ("Dmitry Mendeleev"));
		roster.add("IC 000183" , new Name ("Isaac Newton"));
		roster.add("IC 000074" , new Name ("Dan Xiaopin"));
		roster.remove ("IC 000074");
		roster.remove ("IC 000075");
		roster.remove ("IC 000314");
		// Iterator <String> keys = roster.getKeyIterator();
		// Iterator <Name> names = roster.getValueIterator();
		// System.out.println(keys.hasNext());
		// while (keys.hasNext()) {
		// 	System.out.println (keys.next() + " | " + names.next());
		// }

	}



    private int locateIndex (K key){                                                                           
        int index = 0;                                                    
        while ((index < numberOfEntries) &&
                key.compareTo(entries[index].getKey()) > 0)
            index ++;                                                       
        return index;                                                    
    }   
    
    private void ensureCapacity() {
    	if (numberOfEntries == entries.length) {// array is full
    		entries = Arrays.copyOf(entries, 2*numberOfEntries);
    	}
    }
    
    private void makeRoom (int newPosition) {
        assert (newPosition >= 0 && newPosition <= numberOfEntries);
        for (int idx = numberOfEntries; idx > newPosition; idx --)
            entries[idx] = entries[idx-1];
    }
    
    private void removeGap (int givenPosition) {
        assert (givenPosition >= 0 && givenPosition < numberOfEntries);
        for (int index = givenPosition; index < numberOfEntries; index++){
            entries[index] = entries[index+1];
		}
    }
    
    


	

	@Override
	public V add(K key, V value) {	
		V oldValue = null;
		if (key == null || value == null)
			throw new IllegalArgumentException();
		int keyIndex = locateIndex(key);
		if (keyIndex < numberOfEntries &&
			key.compareTo(entries[keyIndex].getKey()) == 0) {
			oldValue = entries[keyIndex].getValue();
			entries[keyIndex].setValue(value);
			numberOfEntries++;
		}
		else {
			makeRoom (keyIndex);
			entries[keyIndex] = new Entry (key, value);
			ensureCapacity ();
			numberOfEntries++;
		}
		return oldValue;
	}

	@Override
	public V remove(K key) {
		if (key == null)
			throw new IllegalArgumentException();
		V oldValue = null;
		int keyIndex = locateIndex(key);
		if (keyIndex < numberOfEntries && key.compareTo(entries[keyIndex].getKey()) == 0) {
			oldValue = entries[keyIndex].getValue();
			removeGap (keyIndex);		
		}
		return oldValue;
	}

	@Override
	public V getValue(K key) {
		return entries[locateIndex(key)].getValue();
	}

	@Override
	public boolean contains(K key) {
		int low = 0, high = numberOfEntries - 1;
	    int mid;
	    int compValue;
	    while (low <= high) {
	        mid = (low + high)/2;
	        compValue = key.compareTo (entries[mid].getKey());
	        if (compValue == 0)
	            return true;
	        else if (compValue > 0)
	            low = mid + 1;
	        else
	            high = mid - 1;
	    }

		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator ();
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	@Override
	public boolean isEmpty() {
		
		return (numberOfEntries == 0);
	}

	@Override
	public void clear() {
		entries = (Entry<K, V>[]) new Entry [DEFAULT_CAPACITY];
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}
	
	private class  KeyIterator  implements Iterator <K> {
		int cursor = 0;
		
		public boolean hasNext() {
			return (cursor < numberOfEntries);
		}
		
		public K next() {
			if (!hasNext())
				throw new NoSuchElementException();
			K out = entries[cursor].getKey();
			cursor ++;
			return out;
		}	
	}
	
	private class  ValueIterator implements Iterator <V> {
		// to implement
		int cursor = 0;

		public boolean hasNext() {
			return (cursor < numberOfEntries);
		}

		
		public V next() {
			if(!hasNext())
				throw new NoSuchElementException();
			V out = entries[cursor].getValue();
			cursor++;
			return out;
		}
	}

}
