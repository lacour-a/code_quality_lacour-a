import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ForecastParser {

  public static Forecast parseForecastJson(String provider, String jsonString) {
    switch (provider) {
      case "MetaWeather":
        return getMetaWeatherForecast(provider, jsonString);
    }
    return (null);
  }

  private static Forecast getMetaWeatherForecast(String provider, String jsonString) {
    Forecast fc = new Forecast(provider);

    JsonObject parentObject = getParentJsonObject(jsonString);
    JsonArray forecastArray = parentObject.getAsJsonArray("consolidated_weather");

    for (JsonElement day : forecastArray) {
      JsonObject dayObj = day.getAsJsonObject();
      DayForecast dayForecast = new DayForecast(dayObj.get("min_temp").getAsDouble(), dayObj.get("max_temp").getAsDouble());
      fc.addDay(dayForecast);
    }
    return (fc);
  }

  private static JsonObject getParentJsonObject(String jsonString) {
    Gson gson = new Gson();
    JsonElement jsonElementlem = gson.fromJson(jsonString, JsonElement.class);
    JsonObject jsonObject = jsonElementlem.getAsJsonObject();
    return (jsonObject);
  }


}
