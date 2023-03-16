package ru.mirea.playedu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


// Диалог для выбора списка задач
public class FilterListDialog extends DialogFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_filter_list,
                null,
                false);

        // Фон диалога
        getDialog().getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_dialog));
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_filter_list, null, false);
        ListView listsView = view.findViewById(R.id.lists_view);

        String[] mockStrings = {"Homework", "University", "Avrora"};

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter(requireContext(), R.layout.view_list_item, mockStrings);

        listsView.setAdapter(arrayAdapter);

        builder.setView(view);
        return builder.create();
    }
}
