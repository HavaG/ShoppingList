package hu.bme.aut.shoppinglist.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import hu.bme.aut.shoppinglist.R;
import hu.bme.aut.shoppinglist.data.ShoppingList;

public class NewShoppingListDialogFragment extends DialogFragment {

    public static final String TAG = "NewShoppingListDialogFragment";

    public interface NewShoppingListDialogListener {
        void onShoppingListCreated(ShoppingList newList);
    }

    private NewShoppingListDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof NewShoppingListDialogListener) {
            listener = (NewShoppingListDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the NewShoppingListDialogListener interface!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.new_shopping_list)
                .setView(getContentView())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isValid()) {
                            listener.onShoppingListCreated(getShoppingList());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private boolean isValid() {
        return nameEditText.getText().length() > 0;
    }

    private ShoppingList getShoppingList() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.name = nameEditText.getText().toString();
        shoppingList.isBought = alreadyPurchasedCheckBox.isChecked();
        return shoppingList;
    }


    private EditText nameEditText;
    private Spinner categorySpinner;
    private CheckBox alreadyPurchasedCheckBox;

    private View getContentView() {

        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_shopping_list, null);
        nameEditText = contentView.findViewById(R.id.shoppShoppingListNameEditText);
        categorySpinner = contentView.findViewById(R.id.ShoppingListCategorySpinner);
        categorySpinner.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.category_items)));
        alreadyPurchasedCheckBox = contentView.findViewById(R.id.ShoppingListIsPurchasedCheckBox);
        return contentView;
    }
}