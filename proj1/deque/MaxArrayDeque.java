package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> defaultComparator;
    public MaxArrayDeque(Comparator<T> c) {
        defaultComparator = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        } else {
            T biggest = this.get(0);
            for (T i : this) {
                if (defaultComparator.compare(biggest, i) > 0) {
                    biggest = i;
                }
            }
            return biggest;
        }
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        } else {
            T biggest = this.get(0);
            for (T i : this) {
                if (c.compare(biggest, i) > 0) {
                    biggest = i;
                }
            }
            return biggest;
        }
    }
}
