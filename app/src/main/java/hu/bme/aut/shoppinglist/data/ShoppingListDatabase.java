package hu.bme.aut.shoppinglist.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {ShoppingList.class, ShoppingItem.class, Category.class},
        version = 2
)
@TypeConverters({Converters.class})
public abstract class ShoppingListDatabase extends RoomDatabase {
    public abstract ShoppingListDao shoppingListDao();
    public abstract ShoppingItemDao shoppingItemDao();
    public abstract CategoryDao CategoryDao();
}