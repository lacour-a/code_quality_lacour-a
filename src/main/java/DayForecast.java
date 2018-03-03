import com.google.gson.JsonElement;

public class DayForecast {

  public Double minTemp;
  public Double maxTemp;

  public DayForecast(Double minTemp, Double maxTemp) {
    this.minTemp = minTemp;
    this.maxTemp = maxTemp;
  }
}
