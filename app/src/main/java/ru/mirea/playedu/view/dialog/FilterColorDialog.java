package ru.mirea.playedu.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.playedu.callbacks.OnSelectColorFilterCallback;
import ru.mirea.playedu.view.adapter.ColorAdapter;
import ru.mirea.playedu.Constants;
import ru.mirea.playedu.DimensionManager;
import ru.mirea.playedu.HorizontalMarginItemDecoration;
import ru.mirea.playedu.R;
import ru.mirea.playedu.viewmodel.TasksViewModel;

// Диалог выбора цвета для фильтра
public class FilterColorDialog extends DialogFragment implements OnSelectColorFilterCallback{

    private View view;
    private TasksViewModel tasksViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_filter_color,
                null,
                false);

        // Фон диалога
        getDialog().getWindow().setBackgroundDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.shape_dialog));

        tasksViewModel = new ViewModelProvider(requireActivity()).get(TasksViewModel.class);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_filter_color, null, false);
        // Настройка RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.colors_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));
        int[] colors = Constants.getCategoryColors(requireContext());
        recyclerView.setAdapter(new ColorAdapter(colors, this));
        // Рассчет отступов между цветами
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        recyclerView.addItemDecoration(
                new HorizontalMarginItemDecoration(
                        0,
                        DimensionManager.calcHorizontalMargin(
                                metrics.widthPixels,
                                getResources().getDimensionPixelOffset(R.dimen.color_btn_size),
                                colors.length,
                                Constants.DIALOG_COEFFICIENT
                        ),
                        0,
                        0,
                        colors.length));

        builder.setView(view);
        return builder.create();
    }


    @Override
    public void execute(int color) {
        tasksViewModel.filterUserTasksByColor(color);
    }
}

