package proj;

import java.util.Iterator;
import java.util.NoSuchElementException;

import list.SortedListInterface;
import utils.Node;

public class SortedLList <T extends Comparable <? super T>> extends LinkedChainBase <T> 
     implements SortedListInterface <T>, Iterable <T> {
	
	public SortedLList() {
		super();
	}

	public static void main (String[] args) {
		
		SortedLList <String> sslist = new SortedLList <>();
		sslist.addEntry ("Mike");
		sslist.addEntry ("Peter");
		sslist.addEntry ("Alberta");
		sslist.addEntry ("Boris");
		
		
		for (String s: sslist)
			System.out.print (s + " ");
		System.out.println();
		
		sslist.removeEntry("Mike");
		sslist.addEntry("Boris");
		
		for (String s: sslist)
			System.out.print (s + " ");
		System.out.println();
		
		sslist.removeEntry("Alberta");
		sslist.addEntry("Simon");
		
		for (String s: sslist)
			System.out.print (s + " ");
		System.out.println(); 
		 

	}
	
	
	@Override
	public void addEntry(T newEntry) { // Lecture		
		Node <T> before = getNodeBefore (newEntry);
		Node <T> toInsert = new Node <> (newEntry);
		if (before == null)
			addFirstNode (toInsert);
		else 
			addNodeAfter (before, toInsert);
	
	}

	@Override
	public boolean removeEntry(T anEntry) {
		if ( isEmpty() )
			throw new IllegalStateException ("No removal from an empty list");
		Node <T> before = getNodeBefore(anEntry);
		if (before == null) {
			if (anEntry.equals(getFirstNode().getData())) {
				removeFirstNode();
				return true;
			}
			return false;
		}
		Node <T> after = before.getNext();
		if (after == null || anEntry.compareTo (after.getData()) < 0 )
			return false;
		removeNodeAfter (before);
		return true;
		
	}

	@Override
	public int getPosition(T newEntry) {
		int idx = 0;
		int compValue = 0;
		for (Node <T> curr = getFirstNode(); curr != null; curr = curr.getNext()) {
			compValue = newEntry.compareTo(curr.getData());
			if (compValue == 0)
				return idx;
			if (compValue < 0)
				break;
			idx ++;
		}
		return -1 - idx;
	}

	@Override
	public boolean contains(T anEntry) {
		return (getPosition (anEntry) >= 0); 
	}
	
	
	private Node <T> getNodeBefore (T anEntry) {
		Node <T> prevNode = null;
		Node <T> currNode = getFirstNode();
		while (currNode != null && anEntry.compareTo(currNode.getData()) > 0 ) {		
				prevNode = currNode;
				currNode = currNode.getNext();
		}			
		return prevNode;
	}

	@Override
	public Iterator <T> iterator () {
		  return new IteratorForLList();
	  }
	  
	  private class IteratorForLList implements Iterator <T> {
		  
		  private Node <T> nextNode;
		  private Node <T> currNode;
		  private Node <T> prevNode;
		  private boolean nextWasCalled = false;
		  
		  IteratorForLList () {
			  nextNode = getFirstNode();
			  currNode = null;
			  prevNode = null;
			  if (nextNode == null) {
				  throw new IllegalStateException ("Cannot iterate on empty list");
			  }
		  }
		  
		  public boolean hasNext() {
			  return (nextNode != null);
		  }
		  
		  public T next() {
			  if (hasNext()) {
				  T result = nextNode.getData();
				  prevNode = currNode;
				  currNode = nextNode;
				  nextNode = nextNode.getNext();
				  nextWasCalled = true;
				  return result;
			  }
			  else 
				  throw new NoSuchElementException 
				    ("Illegal call: iterator after the end of the list" );
			  
		  }
		  
		  public void remove() {
			  if (!nextWasCalled ) 
				  throw new IllegalStateException();
			  prevNode.setNext(nextNode);
			  currNode = prevNode;
			  nextWasCalled = false;	  
		  }
	  }

}
	  
