package weather;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ForecastParser {

  public static Forecast parseForecastJson(String provider, String jsonString) {
    return getMetaWeatherForecast(provider, jsonString);
  }

  private static Forecast getMetaWeatherForecast(String provider, String jsonString) {
    Forecast fc = new Forecast(provider);

    JsonObject mainObject = getJsonObjectFromString(jsonString);
    JsonArray forecastArray = mainObject.getAsJsonArray("consolidated_weather");

    for (JsonElement day : forecastArray) {
      JsonObject dayObj = day.getAsJsonObject();
      DayForecast dayForecast = new DayForecast(dayObj.get("min_temp").getAsDouble(),
              dayObj.get("max_temp").getAsDouble());
      fc.daysForecast.add(dayForecast);
    }
    return (fc);
  }

  private static JsonObject getJsonObjectFromString(String jsonString) {
    Gson gson = new Gson();
    JsonElement jsonElementlem = gson.fromJson(jsonString, JsonElement.class);
    JsonObject jsonObject = jsonElementlem.getAsJsonObject();
    return (jsonObject);
  }


}
