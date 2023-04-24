package ru.mirea.playedu.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import ru.mirea.playedu.R;
import ru.mirea.playedu.viewmodel.TasksViewModel;


// Диалог для выбора списка задач
public class FilterCategoryDialog extends DialogFragment {

    private View view;
    private TasksViewModel tasksViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_filter_category,
                null,
                false);

        // Фон диалога
        getDialog().getWindow().setBackgroundDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.shape_dialog));
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        view = requireActivity().getLayoutInflater().
                inflate(R.layout.dialog_filter_category, null, false);
        ListView listsView = view.findViewById(R.id.lists_view);

        tasksViewModel = new ViewModelProvider(requireActivity()).get(TasksViewModel.class);
        ArrayList<String> categories = tasksViewModel.getCategoryTitlesList().getValue();

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter(requireContext(), R.layout.view_list_item, categories);

        listsView.setAdapter(arrayAdapter);

        listsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView categoryView = (TextView)view;
                tasksViewModel.filterUserTasksByCategory(categoryView.getText().toString());
            }
        });

        builder.setView(view);
        return builder.create();
    }
}
