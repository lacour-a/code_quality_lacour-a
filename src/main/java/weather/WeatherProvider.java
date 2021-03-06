package weather;

import static weather.JsonUtils.getJsonArrayFromString;

import com.google.gson.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class WeatherProvider {

  public static Forecast getForecast(String city) throws IOException {
    return getForecastFromId(getIdFromLocation(city));
  }

  public static final int LOCATION_NOT_FOUND_ID = -1;

  public static Forecast getForecastFromId(int id) throws IOException {
    if (id == LOCATION_NOT_FOUND_ID) {
      return null;
    }
    Forecast forecast;
    String apiUrl = String.format("https://www.metaweather.com/api/location/%s", String.valueOf(id));

    String apiAnswer = getApiAnswer(apiUrl);
    forecast = ForecastParser.parseForecastJson("MetaWeather", apiAnswer.toString());

    return forecast;
  }

  public static int getIdFromLocation(String city) throws IOException {
    int ret = LOCATION_NOT_FOUND_ID;

    city = URLEncoder.encode(city, "UTF-8");
    String apiUrl = String.format("https://www.metaweather.com/api/location/search/?query=%s", city);

    String apiAnswer = getApiAnswer(apiUrl);
    JsonArray answerJsonObj = getJsonArrayFromString(apiAnswer.toString());

    if (answerJsonObj.size() > 0) {
      ret = answerJsonObj.get(0).getAsJsonObject().get("woeid").getAsInt();
    }
    return ret;
  }

  private static String getApiAnswer(String url) throws IOException {
    StringBuffer jsonAnswer = null;
    HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet(url);
    HttpResponse response = client.execute(request);

    String line;
    jsonAnswer = new StringBuffer("");
    BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    while ((line = bufferedReader.readLine()) != null) {
      jsonAnswer.append(line);
    }
    return jsonAnswer.toString();
  }
}