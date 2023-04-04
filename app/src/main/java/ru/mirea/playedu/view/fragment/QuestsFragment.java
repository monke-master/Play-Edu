package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.ArrayList;

import ru.mirea.playedu.BR;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.DialogAddTaskBinding;
import ru.mirea.playedu.databinding.FragmentQuestsBinding;
import ru.mirea.playedu.model.UserTask;
import ru.mirea.playedu.view.adapter.UserTaskAdapter;
import ru.mirea.playedu.view.dialog.AddTaskDialog;
import ru.mirea.playedu.view.dialog.FilterColorDialog;
import ru.mirea.playedu.view.dialog.FilterListDialog;
import ru.mirea.playedu.view_model.QuestsViewModel;

public class QuestsFragment extends Fragment {
    private QuestsViewModel questsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuestsBinding binding = FragmentQuestsBinding.inflate(getLayoutInflater());
        // Инициализация ViewModel
        questsViewModel = new ViewModelProvider(this).get(QuestsViewModel.class);
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

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                ArrayList<UserTask> userTasks = questsViewModel.getTaskList();
                UserTaskAdapter adapter = new UserTaskAdapter(userTasks);
                binding.userTasksRecyclerView.setAdapter(adapter);
                binding.userTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));;
            }
        });


        //new DeleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Delete task dialog");
        //new CompleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Complete task dialog");

        return binding.getRoot();
    }
}
