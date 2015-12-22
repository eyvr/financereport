package model.report;

import model.value.Month;

import java.util.ArrayList;

public class RelevantMonthsChooser {
    public ArrayList<Month> getRelevantMonths(Month currentMonth)
    {
        ArrayList<Month> result = new ArrayList<>();
        result.add(currentMonth.getPreviousMonth());
        result.add(result.get(result.size()-1).getPreviousMonth());
        result.add(result.get(result.size()-1).getPreviousMonth());
        return result;
    }
}
