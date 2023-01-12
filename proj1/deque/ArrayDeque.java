package deque;

public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    /* |nextFirst - nextLast| = size + 1*/

    public ArrayDeque() {
        items = (Item[]) new Object[4];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(Item item) {
        items[nextFirst] = item;
        nextFirst -= 1;
        if (nextFirst < 0){
            nextFirst = items.length + nextFirst;
        }
        size += 1;
    }

    @Override
    public void addLast(Item item) {
        items[nextLast] = item;
        nextLast += 1;
        if (nextLast >= items.length){
            nextLast = nextLast - items.length;
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
        /* initialize the first and end of array */
        for (int i = 0 ; i < size; i++){
            int j = i + nextFirst + 1;
            if (j >= items.length){
                j = j - items.length ;
            }
            System.out.print(items[j] + " ");
        }
        System.out.println();

    }

    @Override
    public Item removeFirst() {
        if (size <= 0){
            return null;
        } else {
            int index = nextFirst + 1;
            if (index >= items.length){
                index = index - items.length ;
            }
            Item x = items[index];
            size -= 1;
            items[index] = null;
            return x;
        }
    }

    @Override
    public Item removeLast() {
        if (size <= 0){
            return null;
        }else{
            Item x = items[nextLast - 1];
            size -= 1;
            items[nextLast - 1] = null;
            return x;
        }
    }

    @Override
    public Item get(int index) {
        return null;
    }
}
