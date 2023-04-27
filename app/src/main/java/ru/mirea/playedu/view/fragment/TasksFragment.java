package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.ArrayList;
import java.util.Calendar;

import ru.mirea.playedu.DateHelper;
import ru.mirea.playedu.DimensionManager;
import ru.mirea.playedu.HorizontalMarginItemDecoration;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.FragmentTasksBinding;
import ru.mirea.playedu.model.UserTask;
import ru.mirea.playedu.view.adapter.DateAdapter;
import ru.mirea.playedu.view.adapter.UserTaskAdapter;
import ru.mirea.playedu.view.dialog.AddTaskDialog;
import ru.mirea.playedu.view.dialog.CompleteTaskDialog;
import ru.mirea.playedu.view.dialog.FilterColorDialog;
import ru.mirea.playedu.view.dialog.FilterCategoryDialog;
import ru.mirea.playedu.viewmodel.TasksViewModel;

public class TasksFragment extends Fragment {
    private TasksViewModel tasksViewModel;
    private FragmentTasksBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTasksBinding.inflate(getLayoutInflater());
        // Инициализация ViewModel
        tasksViewModel = new ViewModelProvider(requireActivity()).get(TasksViewModel.class);
        binding.setViewModel(tasksViewModel);

        // Recycler-View для календаря
        RecyclerView dateList = binding.dateList;
        DateAdapter dateAdapter = new DateAdapter(date -> {
            tasksViewModel.filterUserTasksByDate(date);
        });
        dateAdapter.setPickedDate(DateHelper.getTodayDate());
        dateAdapter.setDateList(tasksViewModel.getDateList());
        dateList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        dateList.setAdapter(dateAdapter);

        // Расчет отступов между элементами
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int margin =  getResources().getDimensionPixelOffset(R.dimen.date_list_margin);
        dateList.addItemDecoration(
                new HorizontalMarginItemDecoration(
                        0,
                        DimensionManager.calcHorizontalMargin(
                                metrics.widthPixels - margin,
                                getResources().getDimensionPixelOffset(R.dimen.date_item_size),
                               7,
                                1
                        ),
                        0,
                        0,
                        7));
        // Фокусировка на текущей дате
        dateList.scrollToPosition(tasksViewModel.getTodayDayPosition());

        // Фильтр задач
        PowerSpinnerView filterSpinner = binding.filterSpinner;
        filterSpinner.setOnSpinnerItemSelectedListener(
                (OnSpinnerItemSelectedListener<CharSequence>) (oldIndex, oldItem, newIndex, newItem) -> {
                    switch (newIndex) {
                        case 0:
                            FilterCategoryDialog listDialog = new FilterCategoryDialog();
                            listDialog.show(getActivity().getSupportFragmentManager(), "Filter lists dialog");
                            break;
                        case 1:
                            FilterColorDialog colorDialog = new FilterColorDialog();
                            colorDialog.show(getActivity().getSupportFragmentManager(), "Filter color dialog");
                            break;
                    }
                });

        binding.addTaskBtn.setOnClickListener(view -> {
            AddTaskDialog dialog = new AddTaskDialog(tasksViewModel.getDateList().get(tasksViewModel.getCurrentDatePosition()).getTime());
            dialog.show(getActivity().getSupportFragmentManager(), "Add task dialog");
        });

        // Обработка события на нажатую кнопку "Добавить" из диалога
        requireActivity().getSupportFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                tasksViewModel.filterUserTasksByDate(tasksViewModel.getUserTaskFilter().getFilteredDate());
                ArrayList<UserTask> userTasks = tasksViewModel.getUserTasksList().getValue();
                setUserTaskList(userTasks);
            }
        });

        // Обработка нажатия на элемент фильтра
        binding.filterSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<String>() {
            @Override public void onItemSelected(int oldIndex, @Nullable String oldItem, int newIndex, String newItem) {
                switch (newItem) {
                    case "По умолчанию": {
                        tasksViewModel.getUserTaskFilter().setFilteredColor(null);
                        tasksViewModel.getUserTaskFilter().setFilteredCategory(null);
                        tasksViewModel.filterUserTasksByDate(dateAdapter.getPickedDate());
                        break;
                    }
                    case "По списку": {
                        FilterCategoryDialog dialog = new FilterCategoryDialog();
                        dialog.show(getActivity().getSupportFragmentManager(), "Filter list dialog");
                        break;
                    }
                    case "По цвету": {
                        FilterColorDialog dialog = new FilterColorDialog();
                        dialog.show(getActivity().getSupportFragmentManager(), "Filter color dialog");
                        break;
                    }
                }
            }
        });

        // Подписка на данные об отсортированном списке
        tasksViewModel.getUserTasksList().observe(getViewLifecycleOwner(), new Observer<ArrayList<UserTask>>() {
            @Override
            public void onChanged(@Nullable ArrayList<UserTask> value) {
                setUserTaskList(value);
            }
        });

        //new DeleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Delete task dialog");
        //new CompleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Complete task dialog");

        return binding.getRoot();
    }

    // Задание списка элементов для списка пользовательских заданий
    private void setUserTaskList(ArrayList<UserTask> userTasks) {
        UserTaskAdapter adapter = new UserTaskAdapter(userTasks, new UserTaskAdapter.TaskItemListener() {
            @Override
            public void onComplete(UserTask userTask) {
                CompleteTaskDialog dialog = new CompleteTaskDialog(userTask);
                dialog.show(getParentFragmentManager(), "Complete task dialog");
            }

            @Override
            public void onDelete(UserTask userTask) {

            }
        });
        binding.userTasksRecyclerView.setAdapter(adapter);
        binding.userTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
