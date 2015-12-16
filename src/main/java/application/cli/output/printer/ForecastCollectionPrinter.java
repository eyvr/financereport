package application.cli.output.printer;

import application.cli.output.OutputInterface;
import model.forecast.Forecast;
import model.forecast.ForecastCollection;
import model.value.Month;

public class ForecastCollectionPrinter {
    private OutputInterface output;

    public ForecastCollectionPrinter(OutputInterface output) {

        this.output = output;
    }

    public void print(ForecastCollection forecastCollection)
    {
        Month current = forecastCollection.getStartingMonth();
        Forecast optimisic = forecastCollection.getOptimistic();
        Forecast pessimistic = forecastCollection.getPessimistic();
        if (optimisic == null) {
            output.println(String.format("Looks like you won't achieve this goal: %.0f.", forecastCollection.getTargetAmount()));
            return;
        }

        printStats(forecastCollection, current, optimisic, pessimistic);
        printCalendar(current, optimisic.getMonth(), pessimistic.getMonth());
    }

    private void printStats(ForecastCollection forecastCollection, Month current, Forecast optimistic, Forecast pessimistic) {
        output.println(String.format("Target amount: %.0f", forecastCollection.getTargetAmount()));
        output.println("");
        output.println(String.format("Current:     %s", current));
        output.println(String.format("Optimistic:  %s (%d months left, based on %s)",
                optimistic.getMonth(),
                current.getDiff(optimistic.getMonth()),
                optimistic.getReport().getMonthLabel()
        ));
        output.println(String.format("Pessimistic: %s (%d months left, based on %s)",
                pessimistic.getMonth(),
                current.getDiff(pessimistic.getMonth()),
                pessimistic.getReport().getMonthLabel()
        ));
        output.println("");
    }

    private void printCalendar(Month current, Month optimisic, Month pessimistic) {
        Month tmp = new Month(current.getYear(), 1);
        Month last = new Month(pessimistic.getYear(), 12);
        while(!last.isEarlierThan(tmp)) {
            if (tmp.getMonth() == 1) {
                output.print(tmp.getYear() + " |");
            }

            if (tmp.isEqual(optimisic)) {
                output.print("O");
            } else if (tmp.isEqual(pessimistic)) {
                output.print("P");
            } else if (tmp.isEqual(current)) {
                output.print("C");
            } else if (tmp.isEarlierThan(current) || pessimistic.isEarlierThan(tmp)) {
                output.print(" ");
            } else {
                output.print("-");
            }

            if (tmp.getMonth() == 12) {
                output.println("|");
            }
            tmp = tmp.getNextMonth();
        }
        output.println("");
    }
}
