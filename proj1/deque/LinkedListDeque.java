package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;
    // Creat an empty list
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new Node(null, null, null);
        Node firstNode = new Node(x, null, null);
        sentinel.next = firstNode;
        sentinel.prev = firstNode;
        firstNode.prev = sentinel;
        firstNode.next = sentinel;
        size = 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            sentinel = new Node(null, null, null);
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size = 1;
        } else {
            Node prevFirst = sentinel.next;
            Node newFirst = new Node(x, sentinel, prevFirst);
            sentinel.next = newFirst;
            prevFirst.prev = newFirst;
            size += 1;
        }
        /*
        if (sentinel.next == null) {
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            //stock reference to modify sentinel.next.next.prev
            Node temp = sentinel.next;
            sentinel.next = new Node(x, sentinel, temp);
            temp.prev = sentinel.next;
        }
         */
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            sentinel = new Node(null, null, null);
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size = 1;
        } else {
            Node prevLast = sentinel.prev;
            Node newLast = new Node(x, prevLast, sentinel);
            sentinel.prev = newLast;
            prevLast.next = newLast;
            size += 1;
        }
        /*
        if (sentinel.next == null) {
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            //stock reference to modify sentinel.prev.prev.next
            Node temp = sentinel.prev;
            sentinel.prev = new Node(x, temp, sentinel);
            temp.next = sentinel.prev;
        }

         */
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node n = sentinel;
        while (n.next != sentinel) {
            n = n.next;
            System.out.print(n.item + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            Node firstNode = sentinel.next;
            T x = firstNode.item;
            sentinel.next = firstNode.next;
            firstNode.next.prev = sentinel;
            firstNode.item = null;
            size -= 1;
            return x;
        }
    }

    @Override
    public T removeLast() {
        //TODO : change the condition to size
        if (this.isEmpty()){
            return null;
        } else {
            Node prevNode = sentinel.prev;
            T x = prevNode.item;
            sentinel.prev = prevNode.prev;
            prevNode.item = null;
            prevNode.prev.next = sentinel;
            size -= 1;
            return x;
        }
    }

    @Override
    public T get(int index) {
        if (size == 0 || index + 1 > size) {
            return null;
        } else {
            Node current = sentinel.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.item;
        }
    }

}
