package proj;
import utils.Node;

public abstract class LinkedChainBase <T> {
	protected Node <T> head;
	protected int numberOfEntries;
	
	public LinkedChainBase () {
		initializeDataFields();
	}
	
	private void initializeDataFields () {
		head = null;
		numberOfEntries = 0;
	}
	
	public void clear() {
		initializeDataFields ();
	}
	
	public int getLength() {
		return numberOfEntries;
	}
	
	public boolean isEmpty() {
		return (head == null);
	}
	
	public Object [] toArray () {
		Object [] result = new Object[numberOfEntries];
		int idx = 0;
		for (Node <T> curr = head; curr != null; curr = curr.getNext())
			result[idx ++] = curr.getData();
		return result;
	}
	
	public T remove (int givenPosition) {
		Node <T> removed = removeNodeAt (givenPosition);
		return removed.getData();
	}
	
	public T getEntry (int givenPosition) {
		return getNodeAt(givenPosition).getData();
	}
	
	protected Node <T> getFirstNode() {
		return head;
	}
	
	protected Node <T> getNodeAt (int position) {
		if (position < 0 || position >= numberOfEntries)
			throw new IndexOutOfBoundsException 
                         ("searching outside the chain");
		Node <T> curr = head;
		for (int idx = 0; idx < position; idx ++)
			curr = curr.getNext();
		return curr;
	}
	
	protected void addFirstNode (Node <T> toAdd) { // Lecture
		toAdd.setNext(head);
		head = toAdd;
		numberOfEntries ++;
	}
	
	protected void removeFirstNode() {
		head = head.getNext();
	}
	
	protected void addNodeAt (int newPosition, Node <T> toAdd) {
		if (newPosition < 0 || newPosition > numberOfEntries)
			throw new IndexOutOfBoundsException 
                         ("Wrong position for insertion");
		if (newPosition == 0)
			addFirstNode (toAdd);
		else {
			Node <T> prevNode = getNodeAt (newPosition - 1);
			toAdd.setNext(prevNode.getNext());
			prevNode.setNext(toAdd);
			numberOfEntries ++;
		}
		
	}
	
	protected Node <T> removeNodeAt (int givenPosition) {
		if (givenPosition < 0 || givenPosition >= numberOfEntries)
			throw new IndexOutOfBoundsException 
                         ("Wrong position for removal");
		Node <T> removed; 
		if (givenPosition == 0) {
			removed = head;
			removeFirstNode();
		}
		else {
            removed = getNodeAt(givenPosition);
            getNodeAt(givenPosition - 1).setNext(getNodeAt(givenPosition + 1));
		}
		
		removed.setNext(null);
		
		return removed;		
	}
	
	protected final void addNodeAfter (Node <T> currNode, Node <T> toAdd) { // Lecture 
		toAdd.setNext (currNode.getNext());
		currNode.setNext (toAdd);
		numberOfEntries ++;
	}

	
	protected void removeNodeAfter (Node <T> aNode) {
		if (aNode == null)
			removeFirstNode();
		else {
			Node <T> nextNode = aNode.getNext();
			assert (nextNode != null);
			aNode.setNext (nextNode.getNext());
			numberOfEntries --;
 		}
		
	}
	
	
}

