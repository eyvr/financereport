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
        Month m = new Month(2000, 6);

        assertTrue(m.isEarlierThan(new Month(2001, 1)));
        assertTrue(m.isEarlierThan(new Month(2000, 7)));
        assertFalse(m.isEarlierThan(new Month(2000, 6)));
        assertFalse(m.isEarlierThan(new Month(1999, 9)));
        assertFalse(m.isEarlierThan(m));
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
        assertEquals(new Month(2015, 1).toString(), month.toString());
    }

    @Test
    public void testIsEqual() throws Exception {
        assertTrue(new Month(2015, 1).isEqual(new Month(2015, 1)));
        assertFalse(new Month(2014, 1).isEqual(new Month(2015, 1)));
        assertFalse(new Month(2015, 1).isEqual(new Month(2015, 2)));
        assertFalse(new Month(2016, 1).isEqual(new Month(2015, 2)));
    }

    @Test
    public void testGetDiff() throws Exception {
        assertEquals(0, new Month(2015, 1).getDiff(new Month(2015, 1)));
        assertEquals(1, new Month(2015, 1).getDiff(new Month(2015, 2)));
        assertEquals(11, new Month(2015, 1).getDiff(new Month(2015, 12)));
        assertEquals(12, new Month(2015, 1).getDiff(new Month(2016, 1)));
        assertEquals(24, new Month(2015, 1).getDiff(new Month(2017, 1)));
        assertEquals(24, new Month(2017, 1).getDiff(new Month(2015, 1)));
        assertEquals(2, new Month(2017, 4).getDiff(new Month(2017, 2)));
    }
}