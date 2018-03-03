package weather;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TableDisplayerTest {
  List<Forecast> forecasts;

  @Before
  public void setupForecast() {
    this.forecasts = new ArrayList<>();
    Forecast forecast = new Forecast();
    forecast.provider = "MyProvider";
    forecast.daysForecast.add(new DayForecast(1.d, 2.d));
    forecast.daysForecast.add(new DayForecast(2.d, 3.d));
    forecast.daysForecast.add(new DayForecast(3.d, 4.d));
    forecast.daysForecast.add(new DayForecast(4.d, 5.d));
    forecast.daysForecast.add(new DayForecast(10.d, 20.d));
    this.forecasts.add(forecast);
  }

  @Test
  public void testForecastsToStringOneDay() {
    StringBuilder expectedBuilder = new StringBuilder();
    expectedBuilder.append("+------------+--------+\n");
    expectedBuilder.append("|            | J+0    |\n");
    expectedBuilder.append("+------------+--------+\n");
    expectedBuilder.append("| MyProvider | 1°-2°  |\n");
    expectedBuilder.append("+------------+--------+\n");
    String result = TableDisplayer.forecastsToString(1, this.forecasts);
    assertEquals(expectedBuilder.toString(), result);
  }

  @Test
  public void testForecastsToStringTwoDays() {
    StringBuilder expectedBuilder = new StringBuilder();
    expectedBuilder.append("+------------+--------+--------+\n");
    expectedBuilder.append("|            | J+0    | J+1    |\n");
    expectedBuilder.append("+------------+--------+--------+\n");
    expectedBuilder.append("| MyProvider | 1°-2°  | 2°-3°  |\n");
    expectedBuilder.append("+------------+--------+--------+\n");
    String result = TableDisplayer.forecastsToString(2, this.forecasts);
    assertEquals(expectedBuilder.toString(), result);
  }

  @After
  public void teardownForecast() {
  }
}