package utils;

public class DictNode<K,V> {
    private K key;
    private V value;
    private DictNode<K,V> next;
    private DictNode<K,V> prev;
    
    public DictNode (K newKey, V newValue) {
        key = newKey;
        value = newValue;
        next = null;
        prev = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue(){ 
        return value;
    }

    public DictNode<K,V> getNext() {
        return next; 
    }

    public DictNode<K,V> getPrev() {
        return prev;
    }

    public void setValue(V newVal) {
        value = newVal;
    }

    public void setNext(DictNode<K,V> d) {
        next = d;
    }

    public void setPrev(DictNode<K,V> d ) {
        prev = d;
    }


            // accessors, mutators
    
}
