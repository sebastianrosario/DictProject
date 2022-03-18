package proj;

import java.util.Iterator;

import list.ListInterface;
import utils.Node;

public class LListDer <T> extends LinkedChainBase <T> implements ListInterface <T>{
	

	public static void main(String[] args) {
		LListDer <String> testlist = new LListDer <>();
		String citation = "We all live in a yellow submarine";
		String[] words = citation.split ("\\s");
		for (String w: words)
			testlist.add(w);
		testlist.add(2, "guys");
		testlist.add(8, "!!");
		testlist.add(0, "Hi!");
	
		
		Object[] results = testlist.toArray();
		for (Object o: results)
			System.out.print(o + " ");
		
		testlist.remove(8);
		testlist.remove("a");
		testlist.replace(6, "blue");
		System.out.println();
		results = testlist.toArray();
		for (Object o: results)
			System.out.print(o + " ");
		System.out.println();
		System.out.println ("The list contains boy? " + testlist.contains ("boy"));
		System.out.println ("The list contains guys? " + testlist.contains ("guys"));
		System.out.println ("The word at position 3 is " + testlist.getEntry(0));
	}

	// @Override
	// public Iterator<T> iterator() {
	// 	// TODO Auto-generated method stub
	// 	return null;
	// }

	@Override
	public void add(T newEntry) {
		int n = getLength();
		Node <T> toAdd = new Node <> (newEntry);
		addNodeAt (n, toAdd);	
	}

	@Override
	public void add(int newPosition, T newEntry) {
		Node <T> toAdd = new Node <> (newEntry);
		addNodeAt (newPosition, toAdd );
		
	}

	@Override
	public boolean remove(T anEntry) {
		Node <T> curr = getFirstNode();
		if (anEntry.equals(curr.getData())) {
			removeFirstNode();
			return true;
		}
		else {
			Node <T> nextNode = curr.getNext();
		
			while (nextNode != null && !anEntry.equals(nextNode.getData())) {
				curr = nextNode;
				nextNode = nextNode.getNext();
			}
			if (nextNode == null)
				return false;
			curr.setNext(nextNode.getNext());
			numberOfEntries --;		
		}
		return true;
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		
	  Node <T> curr = getNodeAt (givenPosition);
	  T outData = curr.getData();
	  curr.setData(newEntry);
	  return outData;
	}


	@Override
	public boolean contains(T anEntry) {
		Node <T> curr = getFirstNode();
		while (curr != null) {
			if (anEntry.equals(curr.getData()))
					return true;
			curr = curr.getNext();
		}
		return false;
	}

}
