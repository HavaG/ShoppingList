package hu.bme.aut.shoppinglist.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.room.TypeConverter;

public class Converters {
   @TypeConverter
   public static ArrayList<ShoppingItem> fromString(String value) {
      Type listType = new TypeToken<ArrayList<ShoppingItem>>() {}.getType();
      return new Gson().fromJson(value, listType);
   }
   @TypeConverter
   public static String fromArrayList(ArrayList<ShoppingItem> list) {
      Gson gson = new Gson();
      String json = gson.toJson(list);
      return json;
   }
}