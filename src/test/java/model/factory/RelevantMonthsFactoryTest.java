package model.factory;

import model.value.Month;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class RelevantMonthsFactoryTest {

    @Test
    public void testGetRelevantMonths() throws Exception {
        RelevantMonthsFactory testClass = new RelevantMonthsFactory();
        ArrayList<Month> result = testClass.getRelevantMonths(Month.createFromString("2015-01"));

        assertEquals(3, result.size());
        assertTrue(new Month(2014, 12).isEqualTo(result.get(0)));
        assertTrue(new Month(2014, 11).isEqualTo(result.get(1)));
        assertTrue(new Month(2014, 10).isEqualTo(result.get(2)));
    }
}