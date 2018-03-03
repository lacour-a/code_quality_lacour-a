package weather;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtils {
  private JsonUtils() { }

  public static JsonObject getJsonObjectFromString(String jsonString) {
    Gson gson = new Gson();
    JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    return jsonObject;
  }

  public static JsonArray getJsonArrayFromString(String jsonString) {
    Gson gson = new Gson();
    JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
    JsonArray jsonObject = jsonElement.getAsJsonArray();
    return jsonObject;
  }
}
