package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    public class StringComparator implements Comparator<String> {
        public int compare(String a, String b)
        {
            if(a.compareTo(b)>0)
                return 1;
            else if(a.compareTo(b)<0)
                return -1;
            else
                return 0;
        }
    }

    @Test
    public void CompareTest() {
        StringComparator c = new StringComparator();
        MaxArrayDeque mad1 = new MaxArrayDeque(c);
        mad1.addFirst("first");
        mad1.addFirst("middle");
        mad1.addFirst("last");
        System.out.println(mad1.max());
        assertTrue("", mad1.max().equals("middle"));
    }
}
