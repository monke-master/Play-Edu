package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.ArrayList;

import ru.mirea.playedu.databinding.FragmentQuestsBinding;
import ru.mirea.playedu.model.UserTask;
import ru.mirea.playedu.view.adapter.UserTaskAdapter;
import ru.mirea.playedu.view.dialog.AddTaskDialog;
import ru.mirea.playedu.view.dialog.FilterColorDialog;
import ru.mirea.playedu.view.dialog.FilterListDialog;
import ru.mirea.playedu.viewmodel.QuestsViewModel;

public class QuestsFragment extends Fragment {
    private QuestsViewModel questsViewModel;
    private FragmentQuestsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuestsBinding.inflate(getLayoutInflater());
        // Инициализация ViewModel
        questsViewModel = new ViewModelProvider(requireActivity()).get(QuestsViewModel.class);
        // Фильтр задач
        PowerSpinnerView filterSpinner = binding.filterSpinner;
        filterSpinner.setOnSpinnerItemSelectedListener(
                (OnSpinnerItemSelectedListener<CharSequence>) (oldIndex, oldItem, newIndex, newItem) -> {
                    switch (newIndex) {
                        case 0:
                            FilterListDialog listDialog = new FilterListDialog();
                            listDialog.show(getActivity().getSupportFragmentManager(), "Filter lists dialog");
                            break;
                        case 1:
                            FilterColorDialog colorDialog = new FilterColorDialog();
                            colorDialog.show(getActivity().getSupportFragmentManager(), "Filter color dialog");
                            break;
                    }
                });

        binding.addTaskBtn.setOnClickListener(view -> {
            AddTaskDialog dialog = new AddTaskDialog();
            dialog.show(getActivity().getSupportFragmentManager(), "Add task dialog");
        });

        // Обработка события на нажатую кнопку "Добавить" из диалога
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                ArrayList<UserTask> userTasks = questsViewModel.getAllTasksList();
                setUserTaskList(userTasks);
            }
        });

        // Обработка нажатия на элемент фильтра
        binding.filterSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<String>() {
            @Override public void onItemSelected(int oldIndex, @Nullable String oldItem, int newIndex, String newItem) {
                ArrayList<UserTask> userTasks;
                switch (newItem) {
                    case "По умолчанию": {
                        userTasks = questsViewModel.getAllTasksList();
                        setUserTaskList(userTasks);
                        break;
                    }
                    case "По списку": {
                        FilterListDialog dialog = new FilterListDialog();
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
        questsViewModel.getFilteredList().observe(getViewLifecycleOwner(), new Observer<ArrayList<UserTask>>() {
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
        UserTaskAdapter adapter = new UserTaskAdapter(userTasks);
        binding.userTasksRecyclerView.setAdapter(adapter);
        binding.userTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
