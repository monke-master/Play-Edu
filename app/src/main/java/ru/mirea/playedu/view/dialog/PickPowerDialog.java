package ru.mirea.playedu.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.mirea.playedu.R;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.view.adapter.PowerAdapter;
import ru.mirea.playedu.viewmodel.GameViewModel;

public class PickPowerDialog extends DialogFragment {

    private View view;
    private RecyclerView pickablePowers;
    private GameViewModel gameViewModel;
    private int selectedPosition;

    public PickPowerDialog(int position) {
        selectedPosition = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_pick_power,
                null,
                false);

        // Фон диалога
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        view = requireActivity().getLayoutInflater().
                inflate(R.layout.dialog_pick_power, null, false);

        pickablePowers = view.findViewById(R.id.pick_power_list);

        // Инициализация ViewModel
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        bindRecyclerView(gameViewModel.getBoughtPowers().getValue());
        builder.setView(view);
        return builder.create();
    }

    private void bindRecyclerView(ArrayList<Power> rotationPowers) {
        // Табличный RecyclerView для списка купленных сил
        pickablePowers.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        pickablePowers.setNestedScrollingEnabled(false);
        pickablePowers.setAdapter(new PowerAdapter(rotationPowers, new PowerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Power power, int position) {
                if (!gameViewModel.getSelectedPowersList().getValue().contains(power)) gameViewModel.updateSelectedPowersList(power, selectedPosition);
                dismiss();
            }
        }));
    }
}
