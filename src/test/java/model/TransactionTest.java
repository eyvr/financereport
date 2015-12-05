package model;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void testToString() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.JANUARY, 1);
        Date date = cal.getTime();

        Transaction a = new Transaction("sainsbury", date, TransactionType.FOOD, 12.45f);
        Transaction b = new Transaction("pub\nplaistow", date, TransactionType.ENTERTAINMENT, 10000.99f);

        assertEquals("2015-01-01          FOOD     12.45 sainsbury", a.toString());
        assertEquals("2015-01-01 ENTERTAINMENT  10000.99 pub plaistow", b.toString());
    }
}