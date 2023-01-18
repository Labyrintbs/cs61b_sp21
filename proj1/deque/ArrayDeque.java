package deque;


public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int UPFACTOR = 2;
    private double DOWNFACTOR = 0.5;
    /* |nextFirst - nextLast| = size + 1*/

    // Given current index that may exceed array range, transform it to correct in-range index
    private int refreshIndex(int index) {
        if(index < 0 ) {
            return items.length + index;
        } else if (index >= items.length) {
            return index - items.length;
        } else {
            return index;
        }
    }

    private void bigResize(){
        int oriLen = items.length;
        if (size+1 > oriLen) {
            T[] biggerItems = (T[]) new Object[oriLen * UPFACTOR];
            for (int i = 0; i < size; i ++ ) {
                biggerItems[i] = this.get(i);
            }
            items = biggerItems;
            nextFirst = refreshIndex(-1);
            nextLast = refreshIndex(size);
        } else {
            return;
        }
    }

    private void smallResize() {
        int oriLen = items.length;

        if (oriLen > 8 && (size - 1) * 2 < oriLen) {
            T[] smallerItems = (T[]) new Object[(int) (oriLen * DOWNFACTOR)];
            for (int i = 0; i < size; i++) {
                smallerItems[i] = this.get(i);
            }
            items = smallerItems;
            nextFirst = refreshIndex(-1);
            nextLast = refreshIndex(size);
        } else {
            return;
        }

    }
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T item) {
        bigResize();
        items[nextFirst] = item;
        nextFirst -= 1;
        nextFirst = refreshIndex(nextFirst);
        // consider prev_size + 1 for resize?
        /*
        if (nextFirst < 0){
            nextFirst = items.length + nextFirst;
        }
         */
        size += 1;
    }

    @Override
    public void addLast(T item) {
        bigResize();
        items[nextLast] = item;
        nextLast += 1;
        nextLast = refreshIndex(nextLast);
        /*
        if (nextLast >= items.length){
            nextLast = nextLast - items.length;
        }
         */
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
        for (int i = 0 ; i < size; i ++ ) {
            int index = i + nextFirst + 1;
            index = refreshIndex(index);
            System.out.print(items[index] + " ");
        }
        System.out.println();

    }

    @Override
    public T removeFirst() {
        if (size <= 0){
            return null;
        } else {
            smallResize();
            int index = nextFirst + 1;
            index = refreshIndex(index);
            T x = items[index];
            size -= 1;
            items[index] = null;
            nextFirst = refreshIndex(nextFirst+1);
            return x;
        }
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        } else {
            smallResize();
            int index = nextLast - 1;
            index = refreshIndex(index);
            T x = items[index];
            size -= 1;
            items[index] = null;
            nextLast = refreshIndex(nextLast - 1);
            return x;
        }
    }

    @Override
    public T get(int index) {
        if (size == 0 || index + 1 > size) {
            return null;
        } else {
            return items[refreshIndex(index + nextFirst + 1)];
        }
    }
}
