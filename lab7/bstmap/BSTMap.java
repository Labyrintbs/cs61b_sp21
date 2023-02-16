package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private BSTNode root;
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left, right;
        private BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean isLeaf() {
            if (!(this == null) && (this.left == null) && (this.right == null)) {
                return true;
            } else {
                return false;
            }
        }

    }
    public BSTMap() {
        size = 0;
        root = null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return !(getHelper(key, root) == null);
    }


    @Override
    public V get(K key) {
        if (getHelper(key, root) == null) {
            return null;
        } else {
            return getHelper(key, root).value;
        }
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
            node.left = putHelper(key, value, node.left);
        } else {
            node.right = putHelper(key, value, node.right);
        }
        return node;
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

    private class BSTIterator implements Iterator<K> {
        private int visited;
        private Stack<BSTNode> stack;

        public BSTIterator() {
            stack = new Stack<BSTNode>();
            pushLeft(root);
        }

        private void pushLeft(BSTNode node) {
           while (node != null) {
               stack.push(node);
               node = node.left;
           }
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            BSTNode node = stack.pop();
            pushLeft(node.right);
            return node.key;
        }
    }
    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    public void printInorder() {
        printInorderHelper(root);
    }

    private void printInorderHelper(BSTNode node) {
        if (node != null){
            printInorderHelper(node.left);
            System.out.print("Key, Value:" + node.key + " ,"+ node.value + "\n");
            printInorderHelper(node.right);
        }

    }

}
