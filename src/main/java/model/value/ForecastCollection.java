package model.value;

import java.util.ArrayList;

public class ForecastCollection {

    private final float targetAmount;
    private final Month startingMonth;
    private final ArrayList<Forecast> forecasts;

    public ForecastCollection(float targetAmount, Month startingMonth, ArrayList<Forecast> forecasts) {
        this.targetAmount = targetAmount;
        this.startingMonth = startingMonth;
        this.forecasts = forecasts;
    }

    public float getTargetAmount() {
        return targetAmount;
    }

    public Month getStartingMonth() {
        return startingMonth;
    }

    public ArrayList<Forecast> getForecasts() {
        return forecasts;
    }

    public Forecast getOptimistic()
    {
        Forecast earliest = null;
        for (Forecast forecast : this.forecasts)
        {
            if (forecast.getMonth() == null) {
                continue;
            }
            if (earliest == null || forecast.getMonth().isEarlierThan(earliest.getMonth())) {
                earliest = forecast;
            }
        }

        return earliest;
    }

    public Forecast getPessimistic()
    {
        Forecast latest = null;
        for (Forecast forecast : this.forecasts)
        {
            if (forecast.getMonth() == null) {
                continue;
            }
            if (latest == null || latest.getMonth().isEarlierThan(forecast.getMonth())) {
                latest = forecast;
            }
        }

        return latest;
    }
}
