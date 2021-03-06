package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class ForecastParserTester {

  private static final String jsonString = "{\"consolidated_weather\":[{\"id\":4610652647718912,\"weather_state_name\":\"Light Cloud\",\"weather_state_abbr\":\"lc\",\"wind_direction_compass\":\"SW\",\"created\":\"2018-03-03T06:46:39.992130Z\",\"applicable_date\":\"2018-03-03\",\"min_temp\":8.3559999999999999,\"max_temp\":14.038,\"the_temp\":12.359999999999999,\"wind_speed\":9.0541829687441346,\"wind_direction\":223.66412117751594,\"air_pressure\":1000.615,\"humidity\":71,\"visibility\":26.570453551260638,\"predictability\":70},{\"id\":5984174599045120,\"weather_state_name\":\"Light Rain\",\"weather_state_abbr\":\"lr\",\"wind_direction_compass\":\"SW\",\"created\":\"2018-03-03T06:46:41.780370Z\",\"applicable_date\":\"2018-03-04\",\"min_temp\":6.6099999999999994,\"max_temp\":13.212,\"the_temp\":13.254999999999999,\"wind_speed\":8.4729986154537507,\"wind_direction\":230.42715809431996,\"air_pressure\":998.88499999999999,\"humidity\":79,\"visibility\":21.848032490256898,\"predictability\":75},{\"id\":5330295624564736,\"weather_state_name\":\"Showers\",\"weather_state_abbr\":\"s\",\"wind_direction_compass\":\"SW\",\"created\":\"2018-03-03T06:46:45.797400Z\",\"applicable_date\":\"2018-03-05\",\"min_temp\":6.274,\"max_temp\":12.598000000000001,\"the_temp\":11.324999999999999,\"wind_speed\":8.2031002447323633,\"wind_direction\":217.67857604736326,\"air_pressure\":996.06500000000005,\"humidity\":80,\"visibility\":17.663097510538456,\"predictability\":73},{\"id\":5620866738028544,\"weather_state_name\":\"Light Rain\",\"weather_state_abbr\":\"lr\",\"wind_direction_compass\":\"SW\",\"created\":\"2018-03-03T06:46:48.907470Z\",\"applicable_date\":\"2018-03-06\",\"min_temp\":5.9859999999999998,\"max_temp\":12.022,\"the_temp\":11.715,\"wind_speed\":8.1273098051761714,\"wind_direction\":234.95965441865195,\"air_pressure\":996.16000000000008,\"humidity\":82,\"visibility\":9.0434363318221589,\"predictability\":75},{\"id\":6228334094581760,\"weather_state_name\":\"Light Rain\",\"weather_state_abbr\":\"lr\",\"wind_direction_compass\":\"WSW\",\"created\":\"2018-03-03T06:46:52.066730Z\",\"applicable_date\":\"2018-03-07\",\"min_temp\":6.8879999999999999,\"max_temp\":11.43,\"the_temp\":10.934999999999999,\"wind_speed\":10.314547236377953,\"wind_direction\":237.306024161842,\"air_pressure\":1001.5599999999999,\"humidity\":80,\"visibility\":13.835450966356477,\"predictability\":75},{\"id\":5529649215963136,\"weather_state_name\":\"Light Rain\",\"weather_state_abbr\":\"lr\",\"wind_direction_compass\":\"SW\",\"created\":\"2018-03-03T06:46:55.390670Z\",\"applicable_date\":\"2018-03-08\",\"min_temp\":7.847999999999999,\"max_temp\":14.292000000000002,\"the_temp\":10.880000000000001,\"wind_speed\":10.039885667700627,\"wind_direction\":217.75398851966611,\"air_pressure\":1004.22,\"humidity\":82,\"visibility\":null,\"predictability\":75}],\"time\":\"2018-03-03T09:25:26.090520Z\",\"sun_rise\":\"2018-03-03T06:36:50.929137Z\",\"sun_set\":\"2018-03-03T17:52:22.369641Z\",\"timezone_name\":\"LMT\",\"parent\":{\"title\":\"France\",\"location_type\":\"Country\",\"woeid\":23424819,\"latt_long\":\"46.71,1.72\"},\"sources\":[{\"title\":\"BBC\",\"slug\":\"bbc\",\"url\":\"http://www.bbc.co.uk/weather/\",\"crawl_rate\":180},{\"title\":\"Forecast.io\",\"slug\":\"forecast-io\",\"url\":\"http://forecast.io/\",\"crawl_rate\":480},{\"title\":\"HAMweather\",\"slug\":\"hamweather\",\"url\":\"http://www.hamweather.com/\",\"crawl_rate\":360},{\"title\":\"Met Office\",\"slug\":\"met-office\",\"url\":\"http://www.metoffice.gov.uk/\",\"crawl_rate\":180},{\"title\":\"OpenWeatherMap\",\"slug\":\"openweathermap\",\"url\":\"http://openweathermap.org/\",\"crawl_rate\":360},{\"title\":\"Weather Underground\",\"slug\":\"wunderground\",\"url\":\"https://www.wunderground.com/?apiref=fc30dc3cd224e19b\",\"crawl_rate\":720},{\"title\":\"World Weather Online\",\"slug\":\"world-weather-online\",\"url\":\"http://www.worldweatheronline.com/\",\"crawl_rate\":360},{\"title\":\"Yahoo\",\"slug\":\"yahoo\",\"url\":\"http://weather.yahoo.com/\",\"crawl_rate\":180}],\"title\":\"Bordeaux\",\"location_type\":\"City\",\"woeid\":580778,\"latt_long\":\"44.836632,-0.581050\",\"timezone\":\"Europe/London\"}";

  @Test
  public void testMinTempDay1() {
    Double minTemp = 8.356;
    Forecast fc = ForecastParser.parseForecastJson("MetaWeather", jsonString);
    DayForecast df = fc.daysForecast.get(0);
    assertEquals(minTemp, df.minTemp);
  }

  @Test
  public void testGetWoeid() throws UnsupportedEncodingException {
    try {
      int idNewYork = WeatherProvider.getIdFromLocation("New York");
      assertEquals(2459115, idNewYork);
    } catch (IOException exception) {
      fail();
    }
  }
}
