package ru.mirea.playedu;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import ru.mirea.playedu.databinding.FragmentQuestsBinding;

public class QuestsFragment extends Fragment {


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

        binding.addTaskBtn.setOnClickListener(view -> {
            AddTaskDialog dialog = new AddTaskDialog();
            dialog.show(getActivity().getSupportFragmentManager(), "Add task dialog");
        });


        //new DeleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Delete task dialog");
        new CompleteTaskDialog().show(getActivity().getSupportFragmentManager(), "Complete task dialog");


        return binding.getRoot();
    }
}
