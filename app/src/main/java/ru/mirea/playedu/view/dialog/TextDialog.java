package ru.mirea.playedu.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.DialogTextBinding;

// Диалог для отображения текста
public class TextDialog extends DialogFragment {

    private String text;

    public TextDialog(String text) {
        this.text = text;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_text,
                null,
                false);

        // Установка фона диалога
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        return view;
    }

    // Возвращает готовый диалог
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Получение binding и установка view для диалога
        DialogTextBinding binding = DialogTextBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot());

        // Получение view с помощью binding
        // TODO заменить эту херню на View-Model!
        binding.messageTxt.setText(text);

        return builder.create();
    }
}
