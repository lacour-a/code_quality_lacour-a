package weather;

import com.google.gson.JsonArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static weather.JsonUtils.getJsonArrayFromString;

public class WeatherProvider {

  public static Forecast getForecast(String city) {
    return (null);
  }



  public static Forecast getForecastFromId(int id) {
    Forecast fc = new Forecast();
    String apiUrl = String.format("https://www.metaweather.com/api/location/%s", String.valueOf(id));

    String apiAnswer = getApiAnswer(apiUrl);
    fc = ForecastParser.parseForecastJson("MetaWeather", apiAnswer.toString());

    return (fc);
  }

  public static int getIdFromLocation(String city) {
    int ret = 0;
    String apiUrl = String.format("https://www.metaweather.com/api/location/search/?query=%s", city);

    String apiAnswer = getApiAnswer(apiUrl);
    JsonArray answerJsonObj = getJsonArrayFromString(apiAnswer.toString());

    if (answerJsonObj.size() > 0) {
      ret = answerJsonObj.get(0).getAsJsonObject().get("woeid").getAsInt();
    }
    return (ret);
  }

  private static String getApiAnswer(String url) {
    StringBuffer jsonAnswer = null;
    try {
      HttpClient client = new DefaultHttpClient();
      HttpGet request = new HttpGet(url);
      HttpResponse response = client.execute(request);

      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

      jsonAnswer = new StringBuffer("");
      String line;
      while ((line = rd.readLine()) != null) {
        jsonAnswer.append(line);
      }
    } catch (Exception e) {

    }
    return (jsonAnswer.toString());
  }
}