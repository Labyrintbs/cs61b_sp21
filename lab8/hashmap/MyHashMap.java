package hashmap;

import org.checkerframework.checker.units.qual.C;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private static int DEFAULT_SIZE=16;
    private static double DEFAULT_LOAD_FACTOR=0.75;
    // You should probably define some more!
    private int bucketSize;
    private int nodeSize;
    private double maxLoad;
    private HashSet<K> keys;

    /** Constructors */
    public MyHashMap() {this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR);}

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.bucketSize = initialSize;
        this.maxLoad = maxLoad;
        buckets = createTable(initialSize);
        for (int i = 0; i < initialSize; i += 1) {
            buckets[i] = createBucket();
        }
        nodeSize = 0;
        keys = new HashSet<K>();
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.bucketSize; i += 1) {
            buckets[i] = createBucket();
        }
        nodeSize = 0;
        keys = new HashSet<K>();
    }

    @Override
    public boolean containsKey(K key) {
        for (K cmpKey : keys) {
            if (cmpKey.equals(key)) return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key can not be null");
        int hash = getHash(key);
        for (Node n : buckets[hash]) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return nodeSize;
    }

    @Override
    public void put(K key, V value) {
        Node n1 = createNode(key, value);
        if (nodeSize / bucketSize > maxLoad) resize(2);
        int i = getHash(n1.key);
        boolean keyExist = false;
        for (Node n2 : buckets[i]) {
            if (n2.key.equals(key)) {
                keyExist =true;
                n2.value = value;
            }
        }
       if (keyExist == false) {
           buckets[i].add(n1);
           nodeSize += 1;
           keys.add(key);
       }
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


    private int getHash(K key) {
        return Math.floorMod(key.hashCode(), bucketSize);
    }

    private void resize(int resizeFactor) {
        MyHashMap<K, V> newMap = new MyHashMap<K, V>(bucketSize * resizeFactor, maxLoad);
        for (K key : keySet()) {
            newMap.put(key, get(key));
        }
        this.bucketSize = newMap.bucketSize;
        this.buckets = newMap.buckets;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
