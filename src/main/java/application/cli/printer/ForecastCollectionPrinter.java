package application.cli.printer;

import application.cli.output.OutputInterface;
import model.value.Forecast;
import model.value.ForecastCollection;
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
            output.writeln(String.format("Looks like you won't achieve this goal: %.0f.", forecastCollection.getTargetAmount()));
            return;
        }

        printStats(forecastCollection, current, optimisic, pessimistic);
        printCalendar(current, optimisic.getMonth(), pessimistic.getMonth());
    }

    private void printStats(ForecastCollection forecastCollection, Month current, Forecast optimistic, Forecast pessimistic) {
        output.writeln(String.format("Target amount: %.0f", forecastCollection.getTargetAmount()));
        output.writeln("");
        output.writeln(String.format("Current:     %s", current));
        output.writeln(String.format("Optimistic:  %s (%d months left, based on %s)",
                optimistic.getMonth(),
                current.getDiff(optimistic.getMonth()),
                optimistic.getReport().getMonthLabel()
        ));
        output.writeln(String.format("Pessimistic: %s (%d months left, based on %s)",
                pessimistic.getMonth(),
                current.getDiff(pessimistic.getMonth()),
                pessimistic.getReport().getMonthLabel()
        ));
        output.writeln("");
    }

    private void printCalendar(Month current, Month optimisic, Month pessimistic) {
        Month tmp = new Month(current.getYear(), 1);
        Month last = new Month(pessimistic.getYear(), 12);
        while(!last.isEarlierThan(tmp)) {
            if (tmp.getMonth() == 1) {
                output.write(tmp.getYear() + " |");
            }

            if (tmp.isEqual(optimisic)) {
                output.write("O");
            } else if (tmp.isEqual(pessimistic)) {
                output.write("P");
            } else if (tmp.isEqual(current)) {
                output.write("C");
            } else if (tmp.isEarlierThan(current) || pessimistic.isEarlierThan(tmp)) {
                output.write(" ");
            } else {
                output.write("-");
            }

            if (tmp.getMonth() == 12) {
                output.writeln("|");
            }
            tmp = tmp.getNextMonth();
        }
        output.writeln("");
    }
}
