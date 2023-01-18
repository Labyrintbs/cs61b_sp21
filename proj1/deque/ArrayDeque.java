package deque;

import afu.org.checkerframework.checker.oigj.qual.O;

public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int UPFACTOR = 2;
    private double DOWNFACTOR = 0.5;
    /* |nextFirst - nextLast| = size + 1*/

    // Given current index that may exceed array range, transform it to correct in-range index
    private int refreshIndex(int index){
        if (index < 0 ){
            return items.length + index;
        } else if (index >= items.length){
            return index - items.length;
        }
        else{
            return index;
        }
    }

    private void big_resize(){
        int ori_len = items.length;
        if (size+1 > ori_len){
            Item[] bigger_items = (Item[]) new Object[ori_len * UPFACTOR];
            for (int i = 0; i < size; i++){
                bigger_items[i] = this.get(i);
            }
            items = bigger_items;
            nextFirst = refreshIndex(-1);
            nextLast = refreshIndex(size);
        }else{
            return;
        }
    }

    private void small_resize(){
        int ori_len = items.length;

        if (ori_len > 8 && (size - 1) * 2 < ori_len) {
            Item[] smaller_items = (Item[]) new Object[(int) (ori_len * DOWNFACTOR)];
            for (int i = 0; i < size; i++) {
                smaller_items[i] = this.get(i);
            }
            items = smaller_items;
            nextFirst = refreshIndex(-1);
            nextLast = refreshIndex(size);
        }else{
            return;
        }

    }
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(Item item) {
        big_resize();
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
    public void addLast(Item item) {
        big_resize();
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
        for (int i = 0 ; i < size; i++){
            int index = i + nextFirst + 1;
            index = refreshIndex(index);
            System.out.print(items[index] + " ");
        }
        System.out.println();

    }

    @Override
    public Item removeFirst() {
        if (size <= 0){
            return null;
        } else {
            small_resize();
            int index = nextFirst + 1;
            index = refreshIndex(index);
            Item x = items[index];
            size -= 1;
            items[index] = null;
            nextFirst = refreshIndex(nextFirst+1);
            return x;
        }
    }

    @Override
    public Item removeLast() {
        if (size <= 0){
            return null;
        }else{
            small_resize();
            int index = nextLast - 1;
            index = refreshIndex(index);
            Item x = items[index];
            size -= 1;
            items[index] = null;
            nextLast = refreshIndex(nextLast-1);
            return x;
        }
    }

    @Override
    public Item get(int index) {
        if (size == 0 || index + 1 > size) {
            return null;
        } else {
            return items[refreshIndex(index + nextFirst + 1)];
        }
    }
}
