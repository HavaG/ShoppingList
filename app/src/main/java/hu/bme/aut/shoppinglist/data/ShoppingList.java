package hu.bme.aut.shoppinglist.data;

import java.util.ArrayList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shoppinglist")
public class ShoppingList {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "name")
    public String name;


    @ColumnInfo(name = "items")
    public ArrayList<ShoppingItem> items;

    @ColumnInfo(name = "is_bought")
    public boolean isBought;
}