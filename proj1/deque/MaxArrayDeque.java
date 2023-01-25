package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> defaultComparator;
    public MaxArrayDeque(Comparator<T> c) {
        defaultComparator = c;
    }

    public T max() {
        return max(defaultComparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        } else {
            T biggest = this.get(0);
            for (T i : this) {
                if (c.compare(i, biggest) > 0) {
                    biggest = i;
                }
            }
            return biggest;
        }
    }
}
