package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.ArrayList;

import ru.mirea.playedu.UserQuestsAdapter;
import ru.mirea.playedu.UserQuestsModel;
import ru.mirea.playedu.databinding.FragmentQuestsBinding;
import ru.mirea.playedu.view.dialog.AddTaskDialog;
import ru.mirea.playedu.view.dialog.CompleteTaskDialog;
import ru.mirea.playedu.view.dialog.FilterColorDialog;
import ru.mirea.playedu.view.dialog.FilterListDialog;

public class QuestsFragment extends Fragment {

    UserQuestsModel mUserQuest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuestsBinding binding = FragmentQuestsBinding.inflate(getLayoutInflater());

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

        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

            }
        });

        binding.addTaskBtn.setOnClickListener(view -> {
            AddTaskDialog dialog = new AddTaskDialog();
            dialog.show(getActivity().getSupportFragmentManager(), "Add task dialog");
        });

        UserQuestsAdapter adapter = new UserQuestsAdapter(new ArrayList<>());
        binding.userTasksRecyclerView.setAdapter(adapter);
        binding.userTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        //new DeleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Delete task dialog");
        //new CompleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Complete task dialog");


        return binding.getRoot();
    }
}
