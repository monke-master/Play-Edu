package ru.mirea.playedu.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.playedu.R;
import ru.mirea.playedu.viewmodel.GameViewModel;

public class StartGameDialog extends DialogFragment {

    private View view;
    private GameViewModel gameViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_start_game,
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
                inflate(R.layout.dialog_start_game, null, false);
        builder.setView(view);
        Button agreeBtn = (Button) view.findViewById(R.id.yes_btn);
        Button disagreeBtn = (Button) view.findViewById(R.id.no_btn);
        // Инициализация ViewModel
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        gameViewModel.setIsFragmentEnter(false);

        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gameViewModel.setStartGame(true);
            }
        });

        disagreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameViewModel.setStartGame(false);
            }
        });
        return builder.create();
    }
}
