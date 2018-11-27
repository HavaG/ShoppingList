package hu.bme.aut.shoppinglist.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ShoppingListDao {
    @Query("SELECT * FROM shoppinglist")
    List<ShoppingList> getAll();

    @Insert
    long insert(ShoppingList shoppingList);

    @Update
    void update(ShoppingList shoppingList);

    @Delete
    void delete(ShoppingList shoppingList);
}