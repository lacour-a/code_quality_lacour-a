import java.util.ArrayList;
import java.util.List;

public class Forecast {

  public String provider;
  public List<DayForecast> daysForecast = new ArrayList<DayForecast>();

  public Forecast() {
  }

  public Forecast(String provider) {
    this.provider = provider;
  }
}
