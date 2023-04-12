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

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.DialogFightEndBinding;
import ru.mirea.playedu.databinding.DialogTextBinding;

public class FightEndDialog extends DialogFragment {

    private boolean isWin;

    public FightEndDialog(boolean isWin) {
        this.isWin = isWin;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_fight_end,
                null,
                false);

        // Прозрачный фон диалога
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        return view;
    }

    // Возвращает готовый диалог
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Получение binding и установка view для диалога
        DialogFightEndBinding binding = DialogFightEndBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot());

        // Получение view с помощью binding
        // TODO заменить эту херню на View-Model!
        if (!isWin) {
            binding.awardGroup.setVisibility(View.GONE);
            binding.resultHdr.setText(R.string.defeat);
        }

        return builder.create();
    }
}
