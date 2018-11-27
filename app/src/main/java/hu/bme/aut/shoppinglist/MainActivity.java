package hu.bme.aut.shoppinglist;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import hu.bme.aut.shoppinglist.adapter.CategoryAdapter;
import hu.bme.aut.shoppinglist.adapter.ShoppingListAdapter;
import hu.bme.aut.shoppinglist.data.Category;
import hu.bme.aut.shoppinglist.data.ShoppingList;
import hu.bme.aut.shoppinglist.data.ShoppingListDatabase;
import hu.bme.aut.shoppinglist.fragments.NewCategoryDialogFragment;
import hu.bme.aut.shoppinglist.fragments.NewShoppingListDialogFragment;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NewShoppingListDialogFragment.NewShoppingListDialogListener,
        NewCategoryDialogFragment.NewCategoryDialogListener,
        ShoppingListAdapter.ShoppingItemClickListener,
        CategoryAdapter.CategoryClickListener{

    private RecyclerView recyclerView;
    private ShoppingListAdapter adapterList;
    private CategoryAdapter adapterCategory;

    private ShoppingListDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewShoppingListDialogFragment().show(getSupportFragmentManager(), NewShoppingListDialogFragment.TAG);
            }
        });

        database = Room.databaseBuilder(
                getApplicationContext(),
                ShoppingListDatabase.class,
                "shopping-list"
        ).build();

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.MainRecyclerView);
        adapterList = new ShoppingListAdapter(this);
        adapterCategory = new CategoryAdapter(this);
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterList);
    }

    @SuppressLint("StaticFieldLeak")
    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<ShoppingList>>() {

            @Override
            protected List<ShoppingList> doInBackground(Void... voids) {
                return database.shoppingListDao().getAll();
            }

            @Override
            protected void onPostExecute(List<ShoppingList> shoppingLists) {
                adapterList.update(shoppingLists);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onItemChanged(final ShoppingList list) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                database.shoppingListDao().update(list);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("MainActivity", "ShoppingList update was successful");
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onShoppingListCreated(final ShoppingList newList) {
        new AsyncTask<Void, Void, ShoppingList>() {

            @Override
            protected ShoppingList doInBackground(Void... voids) {
                newList.id = database.shoppingListDao().insert(newList);
                return newList;
            }

            @Override
            protected void onPostExecute(ShoppingList shoppingList) {
                adapterList.addItem(shoppingList);
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lists, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_category:
                new NewCategoryDialogFragment().show(getSupportFragmentManager(), NewCategoryDialogFragment.TAG);;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onCategoryCreated(final Category newCategory) {
        new AsyncTask<Void, Void, Category>() {

            @Override
            protected Category doInBackground(Void... voids) {
                newCategory.id = database.CategoryDao().insert(newCategory);
                return newCategory;
            }

            @Override
            protected void onPostExecute(Category newCategory) {
                adapterCategory.addItem(newCategory);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onItemChanged(final Category category) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                database.CategoryDao().update(category);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("MainActivity", "ShoppingList update was successful");
            }
        }.execute();
    }
}
