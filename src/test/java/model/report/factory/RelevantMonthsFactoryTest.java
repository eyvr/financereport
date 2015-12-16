package model.report.factory;

import model.value.Month;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RelevantMonthsFactoryTest {

    @Test
    public void testGetRelevantMonths() throws Exception {
        RelevantMonthsFactory testClass = new RelevantMonthsFactory();
        ArrayList<Month> result = testClass.getRelevantMonths(Month.createFromString("2015-01"));

        assertEquals(3, result.size());
        assertEquals(new Month(2014, 12).toString(), result.get(0).toString());
        assertEquals(new Month(2014, 11).toString(), result.get(1).toString());
        assertEquals(new Month(2014, 10).toString(), result.get(2).toString());
    }
}