import java.util.ArrayList;
import java.util.List;

public class Forecast {

  private String provider;
  private List<DayForecast> daysForecast;

  public Forecast() {
  }

  public Forecast(String provider) {
    this.provider = provider;
  }

  public List<DayForecast> getDaysForecast() {
    return daysForecast;
  }

  public void setDaysForecast(List<DayForecast> daysForecast) {
    this.daysForecast = daysForecast;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public void addDay(DayForecast dayForecast) {
    if (daysForecast == null) {
      daysForecast = new ArrayList<DayForecast>();
    }
    daysForecast.add(dayForecast);
  }
}
