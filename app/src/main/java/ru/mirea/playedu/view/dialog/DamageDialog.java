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
import ru.mirea.playedu.databinding.DialogDamageBinding;
import ru.mirea.playedu.viewmodel.GameViewModel;

// Диалог для отображения нанесенного урона
public class DamageDialog extends DialogFragment {

    private int damage;

    public DamageDialog(int damage) {
        this.damage = damage;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_damage,
                null,
                false);

        // Прозрачный фон диалога
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        return view;
    }

    // Возвращает диалог
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Получение binding и установка view для диалога
        DialogDamageBinding binding = DialogDamageBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot());

        // Получение view с помощью binding
        // TODO заменить эту херню на View-Model!
        if (damage == 0) {
            binding.damageGroup.setVisibility(View.GONE);
            binding.hdr.setText(R.string.no_damage);
        } else {
            binding.damageTxt.setText(Integer.toString(damage));
        }

        return builder.create();
    }

}
