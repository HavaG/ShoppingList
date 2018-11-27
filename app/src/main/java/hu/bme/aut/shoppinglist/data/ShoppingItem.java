package hu.bme.aut.shoppinglist.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shoppingitem")
    public class ShoppingItem {
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        public Long id;

        @ColumnInfo(name = "name")
        public String name;

        @ColumnInfo(name = "description")
        public String description;

        @ColumnInfo(name = "category")
        public String category;

        @ColumnInfo(name="estimated_price")
        public int estimatedPrice;

        @ColumnInfo(name = "is_bought")
        public boolean isBought;
    }