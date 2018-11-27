package hu.bme.aut.shoppinglist.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import hu.bme.aut.shoppinglist.data.Category;
import hu.bme.aut.shoppinglist.R;

public class NewCategoryDialogFragment extends DialogFragment {

    public static final String TAG = "NewCategoryDialogFragment";

    public interface NewCategoryDialogListener {
        void onCategoryCreated(Category newCategory);
    }

    private NewCategoryDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof NewCategoryDialogListener) {
            listener = (NewCategoryDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the NewCategoryDialogListener interface!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.new_category)
                .setView(getContentView())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isValid()) {
                            listener.onCategoryCreated(getCategory());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private boolean isValid() {
        return nameEditText.getText().length() > 0;
    }

    private Category getCategory() {
        Category c = new Category();
        c.name = nameEditText.getText().toString();
        return c;
    }

    private EditText nameEditText;

    private View getContentView() {

        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_category, null);
        nameEditText = contentView.findViewById(R.id.newCategoryNameEditText);
        return contentView;
    }
}