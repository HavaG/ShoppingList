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
import hu.bme.aut.shoppinglist.R;
import hu.bme.aut.shoppinglist.adapter.ShoppingListAdapter;
import hu.bme.aut.shoppinglist.data.ShoppingList;

//TODO:egész átírni ahogy van
public class ShoppingItemAdapter {} /**
        extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder> {

    private final List<ShoppingList> lists;

    private ShoppingItemClickListener listener;

    public ShoppingListAdapter(ShoppingItemClickListener listener) {
        this.listener = listener;
        lists = new ArrayList<>();
    }

    @NonNull
    @Override
    public ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_shopping_list, parent, false);
        return new ShoppingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingViewHolder holder, int position) {
        ShoppingList list = lists.get(position);
        holder.nameTextView.setText(list.name);
        holder.isBoughtCheckBox.setChecked(list.isBought);

        holder.list = list;
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public interface ShoppingItemClickListener{
        void onItemChanged(ShoppingList list);
    }

    class ShoppingViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        CheckBox isBoughtCheckBox;
        ImageButton removeButton;

        ShoppingList list;

        ShoppingViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.ShoppingListNameTextView);
            isBoughtCheckBox = itemView.findViewById(R.id.ShoppingListIsBoughtCheckBox);
            removeButton = itemView.findViewById(R.id.ShoppingListRemoveButton);

            isBoughtCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                    if(list != null){
                        list.isBought = isChecked;
                        listener.onItemChanged(list);
                    }
                }
            });
        }
    }

    public void addItem(ShoppingList item) {
        lists.add(item);
        notifyItemInserted(lists.size() - 1);
    }

    public void update(List<ShoppingList> shoppingItems) {
        lists.clear();
        lists.addAll(shoppingItems);
        notifyDataSetChanged();
    }
}
 */