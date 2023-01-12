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
        Node first_node = new Node(x, null, null);
        sentinel.next = first_node;
        sentinel.prev = first_node;
        first_node.prev = sentinel;
        first_node.next = sentinel;
        size = 1;
    }

    @Override
    public void addFirst(Item x) {
        if (size == 0) {
            sentinel = new Node(null, null, null);
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size = 1;
        }else {
            Node prev_first = sentinel.next;
            Node new_first = new Node(x, sentinel, prev_first);
            sentinel.next = new_first;
            prev_first.prev = new_first;
        }
        size += 1;
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
    public void addLast(Item x) {
        if (size == 0){
            sentinel = new Node(null, null, null);
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size = 1;
        }else {
            Node prev_last = sentinel.prev;
            Node new_last = new Node(x, prev_last, sentinel);
            sentinel.prev = new_last;
            prev_last.next = new_last;
        }
        size += 1;
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
        while (n.next != sentinel){
            n = n.next;
            System.out.print(n.item + " ");
        }
        System.out.println();
    }

    @Override
    public Item removeFirst() {
        if (this.isEmpty()){
            return null;
        }else {
            Node first_node = sentinel.next;
            Item x = first_node.item;
            sentinel.next = first_node.next;
            first_node.next.prev = sentinel;
            first_node.item = null;
            size -= 1;
            return x;
        }
    }

    @Override
    public Item removeLast() {
        //TODO : change the condition to size
        if (this.isEmpty()){
            return null;
        }else {
            Node prev_node = sentinel.prev;
            Item x = prev_node.item;
            sentinel.prev = prev_node.prev;
            prev_node.item = null;
            prev_node.prev.next = sentinel;
            size -= 1;
            return x;
        }
    }

    @Override
    public Item get(int index) {
        if (size == 0 || index + 1 > size) {
            return null;
        } else{
            Node current = sentinel.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.item;
        }
    }

}
