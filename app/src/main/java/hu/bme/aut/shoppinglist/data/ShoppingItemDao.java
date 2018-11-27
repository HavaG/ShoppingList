package hu.bme.aut.shoppinglist.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ShoppingItemDao {
    @Query("SELECT * FROM shoppingitem")
    List<ShoppingItem> getAll();

    @Insert
    long insert(ShoppingItem shoppingItem);

    @Update
    void update(ShoppingItem shoppingItem);

    @Delete
    void delete(ShoppingItem shoppingItem);
}
