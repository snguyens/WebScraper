/**
*
* Data structure to keep track of overall quality + comment associated with a review
* The key in this pair is the overall quality, the value in this pair is the comment
*
*/
public class Pair<K,V> {
    private K key;
    private V value;
 
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
 
    public K getKey() { 
    	return this.key;
    }
    public V getValue() {
    	return this.value;
    }
 
    public void setKey(K key) {
    	this.key = key;
    }
    public void setValue(V value) {
    	this.value = value;
    }
}