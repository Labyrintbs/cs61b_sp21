package deque;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addIsEmptySizeTest(){
        ArrayDeque<String> arrd1 = new ArrayDeque<>();
        assertTrue("A newly initialized ArrayDeque shold be empty, ", arrd1.isEmpty());

        arrd1.addFirst("front");
        assertEquals(1, arrd1.size());

        arrd1.addLast("middle");
        assertEquals(2, arrd1.size());

        arrd1.addLast("back");
        assertEquals(3, arrd1.size());

        arrd1.addFirst("last but not least");
        assertEquals(4, arrd1.size());

        System.out.println("print out deque: ");
        arrd1.printDeque();

    }

    @Test
    /* Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest(){
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest(){

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest(){

        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    public void getItemTest(){
        ArrayDeque<Integer> ad1 =  new ArrayDeque<>();
        ad1.addFirst(5);
        ad1.addFirst(8);
        ad1.addLast(2);
        ad1.addFirst(15);
        assertEquals(15, (int)ad1.get(0));
        assertEquals(8, (int)ad1.get(1));
        assertEquals(5, (int)ad1.get(2));
        assertEquals(2, (int)ad1.get(3));
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void smallLLDequeTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 8; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 15; i > 8; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }
    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }

    @Test

    public void iteratorTeset(){
        ArrayDeque<Integer> ad1 =  new ArrayDeque<>();
        ad1.addFirst(5);
        ad1.addFirst(8);
        ad1.addLast(2);
        ad1.addFirst(15);
        ad1.printDeque();
        for (int i : ad1) {
            System.out.println(i);
        }
    }


    @Test
    public void EqualTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            lld1.addLast(i);
        }

        ArrayDeque<Integer> lld2 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            lld2.addLast(i);
        }

        assertTrue("It should be same for two identical ad", lld1.equals(lld2));
        lld2.addFirst(11);
        assertFalse("It should be not same for not identical ad", lld1.equals(lld2));
    }
}
