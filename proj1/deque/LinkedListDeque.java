package deque;

public class LinkedListDeque<Item> implements Deque<Item>{
    private class Node {
        public Item item;
        public Node next;
        public Node prev;

        public Node(Item i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;
    // Creat an empty list
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        size = 0;
    }

    public LinkedListDeque(Item x) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    @Override
    public void addFirst(Item x) {
        if (sentinel.next == null) {
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            //stock reference to modify sentinel.next.next.prev
            Node temp = sentinel.next;
            sentinel.next = new Node(x, sentinel, temp);
            temp.prev = sentinel.next;
        }
        size += 1;
    }

    @Override
    public void addLast(Item x) {
        if (sentinel.next == null) {
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            //stock reference to modify sentinel.prev.prev.next
            Node temp = sentinel.prev;
            sentinel.prev = new Node(x, temp, sentinel);
            temp.next = sentinel.prev;
        }
        size += 1;
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
        while (n.next != sentinel){
            n = n.next;
            System.out.print(n.item + " ");
        }
        System.out.println();
    }

    @Override
    public Item removeFirst() {
        if (size == 0){
            return null;
        }else if (sentinel.next != null){
            Item x = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return x;
        }
        return null;
    }

    @Override
    public Item removeLast() {
        //TODO : change the condition to size
        if (size == 0){
            return null;
        }else if (sentinel.prev != null) {
            Item x = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return x;
        }
        return null;
    }

    @Override
    public Item get(int index) {
        return null;
    }

}
