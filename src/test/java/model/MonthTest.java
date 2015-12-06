package model;

import model.value.Month;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonthTest {

    @Test
    public void testToString() throws Exception {
        Month m = new Month(2015, 1);
        assertEquals("2015-01", m.toString());
        m = new Month(2012, 2);
        assertEquals("2012-02", m.toString());
        m = new Month(1512, 12);
        assertEquals("1512-12", m.toString());
    }

    @Test
    public void testGetYear() throws Exception {
        Month m = new Month(2015, 1);
        assertEquals(2015, m.getYear());
    }

    @Test
    public void testGetMonth() throws Exception {
        Month m = new Month(2015, 1);
        assertEquals(1, m.getMonth());
    }

    @Test
    public void testIsEarlierThan() throws Exception {
        Month m = new Month(2016, 1);
        Month m2 = new Month(2012, 8);

        assertTrue(m2.isEarlierThan(m));
        assertFalse(m.isEarlierThan(m2));
        assertFalse(m.isEarlierThan(m));
        assertFalse(m2.isEarlierThan(m2));

        Month m3 = new Month(2012, 2);
        assertTrue(m3.isEarlierThan(m2));
        assertFalse(m2.isEarlierThan(m3));
        assertFalse(m.isEarlierThan(m3));
    }

    @Test
    public void testIsEqualTo() throws Exception {
        Month m = new Month(2016, 1);
        Month m2 = new Month(2012, 8);
        Month m3 = new Month(2016, 1);
        assertTrue(m.isEqualTo(m3));
        assertTrue(m3.isEqualTo(m));
        assertFalse(m2.isEqualTo(m3));
        assertFalse(m2.isEqualTo(m));
        assertFalse(m.isEqualTo(m2));
        assertFalse(m3.isEqualTo(m2));
    }

    @Test
    public void testGetNextMonth() throws Exception {
        Month m = new Month(2016, 10);
        assertEquals("2016-10", m.toString());
        assertEquals("2016-11", m.getNextMonth().toString());
        assertEquals("2016-12", m.getNextMonth().getNextMonth().toString());
        assertEquals("2017-01", m.getNextMonth().getNextMonth().getNextMonth().toString());
    }

    @Test
    public void testGetDaysCount() throws Exception {
        assertEquals(31, new Month(2015, 1).getDaysCount());
        assertEquals(28, new Month(2015, 2).getDaysCount());
        assertEquals(31, new Month(2015, 3).getDaysCount());
        assertEquals(30, new Month(2015, 4).getDaysCount());
        assertEquals(31, new Month(2015, 5).getDaysCount());
        assertEquals(30, new Month(2015, 6).getDaysCount());
        assertEquals(31, new Month(2015, 7).getDaysCount());
        assertEquals(31, new Month(2015, 8).getDaysCount());
        assertEquals(30, new Month(2015, 9).getDaysCount());
        assertEquals(31, new Month(2015, 10).getDaysCount());
        assertEquals(30, new Month(2015, 11).getDaysCount());
        assertEquals(31, new Month(2015, 12).getDaysCount());

        assertEquals(29, new Month(2000, 2).getDaysCount());
    }

    @Test
    public void testGetMonthStart() throws Exception {
        assertEquals("2015-01-01", new Month(2015, 1).getMonthStart());
        assertEquals("2015-02-01", new Month(2015, 1).getNextMonth().getMonthStart());
    }

    @Test
    public void testGetPreviousMonth() throws Exception {
        Month m = new Month(2016, 3);
        assertEquals("2016-03", m.toString());
        assertEquals("2016-02", m.getPreviousMonth().toString());
        assertEquals("2016-01", m.getPreviousMonth().getPreviousMonth().toString());
        assertEquals("2015-12", m.getPreviousMonth().getPreviousMonth().getPreviousMonth().toString());
    }

    @Test
    public void testCreateFromString() throws Exception {
        Month month = Month.createFromString("2015-01");
        assertTrue(new Month(2015, 1).isEqualTo(month));
    }
}