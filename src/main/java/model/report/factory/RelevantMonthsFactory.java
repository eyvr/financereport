package model.report.factory;

import model.value.Month;

import java.util.ArrayList;

public class RelevantMonthsFactory {
    public ArrayList<Month> getRelevantMonths(Month currentMonth)
    {
        ArrayList<Month> result = new ArrayList<>();
        result.add(currentMonth.getPreviousMonth());
        result.add(currentMonth.getPreviousMonth().getPreviousMonth());
        result.add(currentMonth.getPreviousMonth().getPreviousMonth().getPreviousMonth());
        return result;
    }
}
