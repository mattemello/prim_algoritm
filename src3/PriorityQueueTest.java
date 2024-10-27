package src3;

import java.beans.Transient;
import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {

    class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    }

    private Integer i1, i2, i3;
    private PriorityQueue<Integer> PriorityQueueInt;

    @Before
    public void CreatePriorityQueueInt() {
        i1 = -7;
        i2 = 9;
        i3 = 11;

        PriorityQueueInt = new src3.PriorityQueue<Integer>(new IntegerComparator());
    }

    @Test
    public void testEmpty_zeroEl() {
        assertTrue(PriorityQueueInt.empty());
    }

    @Test
    public void testEmpty_oneEl() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        assertFalse(PriorityQueueInt.empty());
    }

    @Test
    public void testPushTop_oneEl() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        assertEquals(i1, PriorityQueueInt.top());
    }

    @Test
    public void testPushTop_twoEl() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        PriorityQueueInt.push(i2);
        assertEquals(i1, PriorityQueueInt.top());
    }

    @Test
    public void testPop_oneEl() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        PriorityQueueInt.push(i2);
        PriorityQueueInt.pop();
        assertEquals(i2, PriorityQueueInt.top());
    }

    @Test
    public void testPop_twoEl() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        PriorityQueueInt.push(i2);
        PriorityQueueInt.push(i3);
        PriorityQueueInt.pop();
        PriorityQueueInt.pop();
        assertEquals(i3, PriorityQueueInt.top());
    }

    @Test
    public void testContains_twoElFalse() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        PriorityQueueInt.push(i2);
        assertFalse(PriorityQueueInt.contains(i3));
    }

    @Test
    public void testPutTwoSameEl() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        PriorityQueueInt.push(i1);
        assertTrue(PriorityQueueInt.contains(i1));
    }

    @Test
    public void testContains_twoEl() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        PriorityQueueInt.push(i2);
        assertTrue(PriorityQueueInt.contains(i2));
    }

    @Test
    public void testRemove_oneEl() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        PriorityQueueInt.push(i2);
        PriorityQueueInt.push(i3);
        PriorityQueueInt.remove(i2);

        assertFalse(PriorityQueueInt.contains(i2));
    }

    @Test
    public void testRemove_twoEl() throws PriorityQueueExpectation {
        PriorityQueueInt.push(i1);
        PriorityQueueInt.push(i2);
        PriorityQueueInt.push(i3);
        PriorityQueueInt.remove(i2);
        PriorityQueueInt.remove(i3);

        assertFalse(PriorityQueueInt.contains(i3) || PriorityQueueInt.contains(i2));
    }

    // ---------------String-------------------------

    class StringComparator implements Comparator<String> {
        @Override
        public int compare(String i1, String i2) {
            return i1.compareTo(i2);
        }
    }

    private String s1, s2, s3;
    private PriorityQueue<String> PriorityQueueStr;

    @Before
    public void CreatePriorityQueueStr() {
        s1 = "ciao";
        s2 = "prova";
        s3 = "bao";

        PriorityQueueStr = new src3.PriorityQueue<String>(new StringComparator());
    }

    @Test
    public void testEmpty_zeroElStr() {
        assertTrue(PriorityQueueStr.empty());
    }

    @Test
    public void testEmpty_oneElStr() throws PriorityQueueExpectation {
        PriorityQueueStr.push(s1);
        assertFalse(PriorityQueueStr.empty());
    }

    @Test
    public void testPushTop_oneElStr() throws PriorityQueueExpectation {
        PriorityQueueStr.push(s1);
        assertEquals(s1, PriorityQueueStr.top());
    }

    @Test
    public void testPushTop_twoElStr() throws PriorityQueueExpectation {
        PriorityQueueStr.push(s1);
        PriorityQueueStr.push(s2);
        assertEquals(s1, PriorityQueueStr.top());
    }

    @Test
    public void testPop_oneElStr() throws PriorityQueueExpectation {
        PriorityQueueStr.push(s1);
        PriorityQueueStr.push(s2);
        PriorityQueueStr.pop();
        assertEquals(s2, PriorityQueueStr.top());
    }

    @Test
    public void testPop_twoElStr() throws PriorityQueueExpectation {
        PriorityQueueStr.push(s1);
        PriorityQueueStr.push(s2);
        PriorityQueueStr.push(s3);
        PriorityQueueStr.pop();
        PriorityQueueStr.pop();
        assertEquals(s3, PriorityQueueStr.top());
    }

    @Test
    public void testContains_twoElFalseStr() throws PriorityQueueExpectation {
        PriorityQueueStr.push(s1);
        PriorityQueueStr.push(s2);
        assertFalse(PriorityQueueStr.contains(s3));
    }

    @Test
    public void testContains_twoElStr() throws PriorityQueueExpectation {
        PriorityQueueStr.push(s1);
        PriorityQueueStr.push(s2);
        assertTrue(PriorityQueueStr.contains(s2));
    }

    @Test
    public void testRemove_oneElStr() throws PriorityQueueExpectation {
        PriorityQueueStr.push(s1);
        PriorityQueueStr.push(s2);
        PriorityQueueStr.push(s3);
        PriorityQueueStr.remove(s2);

        assertFalse(PriorityQueueStr.contains(s2));
    }

    @Test
    public void testRemove_twoElStr() throws PriorityQueueExpectation {
        PriorityQueueStr.push(s1);
        PriorityQueueStr.push(s2);
        PriorityQueueStr.push(s3);
        PriorityQueueStr.remove(s2);
        PriorityQueueStr.remove(s3);

        assertFalse(PriorityQueueStr.contains(s3) && PriorityQueueStr.contains(s2));
    }
}
