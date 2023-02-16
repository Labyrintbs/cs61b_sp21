package bstmap;

import edu.princeton.cs.algs4.BST;

import java.security.PrivateKey;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private BSTNode root;
    private BSTNode successor;
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
        Set<K> set = new HashSet<K>();
        keySet(set, root);
        return set;
    }
    private void keySet(Set<K> set, BSTNode node) {
        if (node == null) {
            return;
        }
        keySet(set, node.left);
        set.add(node.key);
        keySet(set, node.right);
    }

    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        root = removeHelper(root, key);
        size -= 1;
        return successor.value;
    }

    private BSTNode removeHelper (BSTNode node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeHelper(node.left, key);
        } else if (cmp > 0) {
            node.right = removeHelper(node.right, key);
        } else {
            successor = node;
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
               node.right = swapSmallest(node.right, node);
            }
        }
       return node;
    }

    private BSTNode removeHelper (BSTNode node, K key, V value) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeHelper(node.left, key);
        } else if (cmp > 0) {
            node.right = removeHelper(node.right, key);
        } else {
            if (node.value == value){
                successor = node;
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
                node.right = swapSmallest(node.right, node);
            }
            } else {
                return node;
            }
        }
        return node;
    }
    // swap the L(T.right) with the R(T)
    private BSTNode swapSmallest(BSTNode L, BSTNode R) {
       if (L.left == null) {
           R.key = L.key;
           R.value = L.value;
           return L.right;
       } else {
           L = swapSmallest(L.left, R);
           return L;
       }
    }
    @Override
    public V remove(K key, V value) {
        root = removeHelper(root, key, value);
        if (successor != null) {
            size -= 1;
            return successor.value;
        } else {
            return null;
        }
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
