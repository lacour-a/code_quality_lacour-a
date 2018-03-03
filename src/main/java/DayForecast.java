public class DayForecast {

  private Double minTemp;
  private Double maxTemp;

  public DayForecast(Double minTemp, Double maxTemp) {
    this.minTemp = minTemp;
    this.maxTemp = maxTemp;
  }

  public Double getMinTemp() {
    return minTemp;
  }

  public void setMinTemp(Double minTemp) {
    this.minTemp = minTemp;
  }

  public Double getMaxTemp() {
    return maxTemp;
  }

  public void setMaxTemp(Double maxTemp) {
    this.maxTemp = maxTemp;
  }
}
