package weather;

import java.util.ArrayList;
import java.util.List;

public class Main {
  private static void displayUsage() {
    System.out.println("Usage: weather.jar <city-name> [-j <number-of-days>]");
    System.out.println("\t- number-of-days: Range: 1-5. Default is 5.");
  }

  private static void weather(String cityName, int numberOfDays) {
    Forecast forecast = WeatherProvider.getForecast(cityName);
    List<Forecast> forecasts = new ArrayList<>();
    forecasts.add(forecast);
    System.out.println(TableDisplayer.forecastsToString(numberOfDays, forecasts));
  }

  public static void main(String[] args) {
    if ((args.length != 1 && args.length != 3)
            || (args.length == 3 && !args[1].equals("-j"))) {
      displayUsage();
      System.exit(1);
    } else if (args.length == 1) {
      weather(args[0], 5);
    } else {
      try {
        weather(args[0], Integer.parseInt(args[2]));
      } catch (NumberFormatException exception) {
        System.err.println("Invalid number: " + args[2]);
      }
    }
  }
}
