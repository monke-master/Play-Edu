package ru.mirea.playedu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ru.mirea.playedu.databinding.FragmentQuestsBinding;



public class QuestsFragment extends Fragment {

    // Счетчик количества нажатий на элементы списка фильтров
    private int selectionCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuestsBinding binding = FragmentQuestsBinding.inflate(getLayoutInflater());

        Spinner filterSpinner = binding.filterSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.filterItems,
                R.layout.view_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        // Выбор фильтра
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        FilterListDialog listDialog = new FilterListDialog();
                        listDialog.show(getActivity().getSupportFragmentManager(), "Filter lists dialog");
                        break;
                    case 1:
                        if (selectionCount > 0) {
                            FilterColorDialog colorDialog = new FilterColorDialog();
                            colorDialog.show(getActivity().getSupportFragmentManager(), "Filter color dialog");
                        } else selectionCount++;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return binding.getRoot();
    }
}