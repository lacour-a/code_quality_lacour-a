package weather;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TableDisplayer {
  private static final int MIN_DAY_COLUMN_WIDTH = 6;
  private int numberOfDays;
  private List<Forecast> forecasts;
  int providerColumnWidth;
  private List<Integer> daysColumnWidths;
  private StringBuilder output;

  public static String forecastsToString(int numberOfDays, List<Forecast> forecasts) {
    TableDisplayer displayer = new TableDisplayer(numberOfDays, forecasts);
    return displayer.convertToString();
  }

  private TableDisplayer(int numberOfDays, List<Forecast> forecasts) {
    this.numberOfDays = numberOfDays;
    this.forecasts = forecasts;
    this.output = new StringBuilder();
  }

  private String convertToString() {
    this.computeNameColumnWidth();
    this.computeDaysColumnWidths();

    this.printHorizontalBorder();
    this.printHeaderRow();
    this.printHorizontalBorder();
    for (Forecast forecast : this.forecasts) {
      this.printForecastRow(forecast);
      this.printHorizontalBorder();
    }
    return this.output.toString();
  }

  private void computeNameColumnWidth() {
    this.providerColumnWidth = this.forecasts.stream()
            .map(forecast -> forecast.provider.length())
            .max(Comparator.naturalOrder())
            .get();
  }

  private static int getTemperatureWidth(DayForecast dayForecast) {
    return getTemperatureString(dayForecast).length();
  }

  private Integer getMaxTemperatureColumnWidthForDay(int dayNumber) {
    return this.forecasts.stream()
            .map(forecast -> forecast.daysForecast.get(dayNumber))
            .map(TableDisplayer::getTemperatureWidth)
            .max(Comparator.naturalOrder())
            .get();
  }

  private void computeDaysColumnWidths() {
    this.daysColumnWidths = IntStream.range(0, this.numberOfDays)
            .mapToObj(dayNumber -> this.getMaxTemperatureColumnWidthForDay(dayNumber))
            .map(width -> Integer.max(width, MIN_DAY_COLUMN_WIDTH))
            .collect(Collectors.toList());
  }

  private void printHorizontalBorder() {
    this.output.append(String.format("+-%s-+", repeatCharToString('-', this.providerColumnWidth)));
    for (Integer dayColumnWidth : this.daysColumnWidths) {
      this.output.append(String.format("-%s-+", repeatCharToString('-', dayColumnWidth)));
    }
    this.output.append('\n');
  }

  private void printHeaderRow() {
    this.output.append(String.format("| %s |", repeatCharToString(' ', this.providerColumnWidth)));
    for (int dayNumber = 0; dayNumber < this.numberOfDays; ++dayNumber) {
      String cellContents = String.format("J+%d", dayNumber);
      this.output.append(String.format(" %s%s |",
              cellContents,
              repeatCharToString(' ',
                      this.daysColumnWidths.get(dayNumber) - cellContents.length())));
    }
    this.output.append('\n');
  }

  private void printForecastRow(Forecast forecast) {
    this.output.append(String.format("| %s%s |",
            forecast.provider,
            repeatCharToString(' ', this.providerColumnWidth - forecast.provider.length())));
    for (int dayNumber = 0; dayNumber < this.numberOfDays; ++dayNumber) {
      this.printForecastTemperatureCell(
              forecast.daysForecast.get(dayNumber),
              this.daysColumnWidths.get(dayNumber));
    }
    this.output.append('\n');
  }

  private void printForecastTemperatureCell(DayForecast dayForecast, int width) {
    String cellContents = getTemperatureString(dayForecast);
    this.output.append(String.format(" %s%s |",
            cellContents,
            repeatCharToString(' ', width - cellContents.length())));
  }

  private static String getTemperatureString(DayForecast dayForecast) {
    return String.format("%dC-%dC",
            dayForecast.minTemp.intValue(),
            dayForecast.maxTemp.intValue());
  }

  private static String repeatCharToString(char c, int repetitions) {
    char[] repeat = new char[repetitions];
    Arrays.fill(repeat, c);
    return new String(repeat);
  }
}
