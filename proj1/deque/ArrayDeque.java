package deque;


import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int UPFACTOR = 2;
    private double DOWNFACTOR = 0.5;
    /* |nextFirst - nextLast| = size + 1*/

    // Given current index that may exceed array range, transform it to correct in-range index
    private int refreshIndex(int index) {
        if (index < 0) {
            return items.length + index;
        } else if (index >= items.length) {
            return index - items.length;
        } else {
            return index;
        }
    }

    private void bigResize() {
        int oriLen = items.length;
        if (size + 1 > oriLen) {
            T[] biggerItems = (T[]) new Object[oriLen * UPFACTOR];
            for (int i = 0; i < size; i++ ) {
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

    private class ArrayDequeIterator implements Iterator<T> {
        private int iterPosition;
        ArrayDequeIterator() {
            iterPosition = 0;
        }
        @Override
        public boolean hasNext() {
            return iterPosition < size;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T nextItem = get(iterPosition);
                iterPosition += 1;
                return nextItem;
            } else {
                return null;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
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
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        /* initialize the first and end of array */
        for (int i = 0; i < size; i++) {
            int index = i + nextFirst + 1;
            index = refreshIndex(index);
            System.out.print(items[index] + " ");
        }
        System.out.println();

    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        } else {
            smallResize();
            int index = nextFirst + 1;
            index = refreshIndex(index);
            T x = items[index];
            size -= 1;
            items[index] = null;
            nextFirst = refreshIndex(nextFirst + 1);
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

    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque ad = (Deque) o;
            if (ad.size() != this.size()) {
                return false;
            } else {
                for (int i = 0; i < size; i += 1) {
                    if (!this.get(i).equals(ad.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }
}
