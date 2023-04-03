package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import ru.mirea.playedu.BR;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.DialogAddTaskBinding;
import ru.mirea.playedu.databinding.FragmentQuestsBinding;
import ru.mirea.playedu.view.dialog.AddTaskDialog;
import ru.mirea.playedu.view.dialog.FilterColorDialog;
import ru.mirea.playedu.view.dialog.FilterListDialog;
import ru.mirea.playedu.view_model.QuestsViewModel;

public class QuestsFragment extends Fragment {
    private QuestsViewModel questsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // FragmentQuestsBinding binding = FragmentQuestsBinding.inflate(getLayoutInflater());
        FragmentQuestsBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_quests, container, false);

        questsViewModel = new QuestsViewModel();
        binding.setVariable(BR.viewModel, questsViewModel);
        binding.executePendingBindings();
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



        //new DeleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Delete task dialog");
        //new CompleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Complete task dialog");

        return binding.getRoot();
    }
}
