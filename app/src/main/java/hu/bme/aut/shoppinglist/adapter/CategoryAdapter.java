package hu.bme.aut.shoppinglist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hu.bme.aut.shoppinglist.MainActivity;
import hu.bme.aut.shoppinglist.R;
import hu.bme.aut.shoppinglist.adapter.ShoppingListAdapter;
import hu.bme.aut.shoppinglist.data.Category;
import hu.bme.aut.shoppinglist.data.ShoppingList;

//TODO:egész átírni ahogy van
public class CategoryAdapter
        extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final List<Category> categories;

    private CategoryClickListener listener;

    public CategoryAdapter(CategoryClickListener listener) {
        this.listener = listener;
        categories = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.nameTextView.setText(category.name);

        holder.category = category;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface CategoryClickListener{
        void onItemChanged(Category category);
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;

        Category category;

        CategoryViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.ShoppingListNameTextView);
        }
    }

    public void addItem(Category item) {
        categories.add(item);
        notifyItemInserted(categories.size() - 1);
    }

    public void update(List<Category> newCategories) {
        categories.clear();
        categories.addAll(newCategories);
        notifyDataSetChanged();
    }
}