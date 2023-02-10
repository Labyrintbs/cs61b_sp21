package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    private int size;
    private BSTNode root;
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left, right;
        private BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

    }
    public BSTMap() {
        size = 0;
        root = null;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(K key) {
        throw new UnsupportedOperationException();
    }


    @Override
    public V get(K key) {
        return getHelper(key, root).value;
    }

    private BSTNode getHelper(K key, BSTNode node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return getHelper(key, node.left);
        } else {
            return getHelper(key, node.right);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    private BSTNode putHelper(K key, V value, BSTNode node) {
        if (node == null) {
            size += 1;
            return new BSTNode(key, value);
        } else if (key.compareTo(node.key) < 0) {
            return putHelper(key, value, node.left);
        } else {
            return putHelper(key, value, node.right);
        }
    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
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
        return null;
    }

    void printInorder() {
    }
}
