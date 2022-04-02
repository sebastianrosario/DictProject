package dictionary;

import utils.DictNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDictionary <K, V> implements DictionaryInterface <K, V> {
	
	
	
	private DictNode <K, V> head;
	private int numberOfEntries;
	
	public LinkedDictionary () {
		head = null;
		numberOfEntries = 0;
	}

	public static void main(String[] args) {
		LinkedDictionary <String, Name>  roster = new LinkedDictionary <> ();
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

		
		
		Iterator <String> keys = roster.getKeyIterator();
		Iterator <Name> names = roster.getValueIterator();
		
		while (keys.hasNext()) {
			System.out.println (keys.next() + " | " + names.next());
		}

	}

	@Override
	public V add(K key, V value) {
		for (DictNode <K, V> curr = head; curr != null; curr = curr.getNext()) {
			if (key.equals(curr.getKey() ) ) {
				V oldValue = curr.getValue();
				curr.setValue (value);
				return oldValue;
			}
		}
		DictNode <K, V > toAdd = new DictNode (key, value);
		toAdd.setNext(head);
		head = toAdd;
		numberOfEntries ++;
		return null;
	}

	@Override
	public V remove(K key) {
		DictNode<K,V> prev = null;
		for (DictNode<K,V> curr = head; curr != null; curr = curr.getNext()) {
			if (key.equals(curr.getKey() ) ) {
				V oldValue = curr.getValue();
				if (prev == null) {
					head = head.getNext();
				}
				else
					prev.setNext(curr.getNext());
				numberOfEntries --;
				return oldValue;
			}
			prev = curr;
		}
		return null;
	}

	@Override
	public V getValue(K key) {
		for (DictNode <K, V> curr = head; curr != null; curr = curr.getNext()) {
			if (key.equals(curr.getKey() ) )  
				return curr.getValue();
		}
		return null;
	}


	@Override
	public boolean contains(K key) {
		return (getValue (key) == null);
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
		
		return numberOfEntries == 0;
	}

	@Override
	public void clear() {
		head = null;
		numberOfEntries = 0;
		
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}
	
	private class KeyIterator implements Iterator <K> {
		private DictNode <K, V> nextNode;
	    public KeyIterator () {
	    	nextNode = head;
	    }
	    
	    public boolean hasNext() {
	    	return (nextNode != null);
	    }
	    
	    public K next() {
	    	if (!hasNext())
	    		throw new NoSuchElementException ("No more iterations ");
	    	K out = nextNode.getKey();
	    	nextNode = nextNode.getNext();
	    	return out;
	    }
	}
	private class ValueIterator implements Iterator <V> {
		private DictNode <K, V> nextNode;
	    public ValueIterator () {
	    	nextNode = head;
	    }
	    
	    public boolean hasNext() {
	    	return (nextNode != null);
	    }
	    
	    public V next() {
	    	if (!hasNext())
	    		throw new NoSuchElementException ("No more iterations ");
	    	V out = nextNode.getValue();
	    	nextNode = nextNode.getNext();
	    	return out;
	    }
	}

}
