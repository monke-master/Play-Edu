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

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.DialogFightEndBinding;
import ru.mirea.playedu.databinding.DialogTextBinding;
import ru.mirea.playedu.viewmodel.GameViewModel;

public class FightEndDialog extends DialogFragment {

    private GameViewModel gameViewModel;

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


        // Инициализация ViewModel
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        binding.enemyImg.setImageResource(gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getImageId());
        switch (gameViewModel.getCurrentBattleResult()) {
            case DEFEAT:
                binding.winBattleGroup.setVisibility(View.GONE);
                binding.awardHdr.setText("");
                binding.resultHdr.setText(R.string.defeat);
                binding.button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gameViewModel.restartGame();
                        getDialog().dismiss();
                    }
                });
                break;
            case WIN_BATTLE:
                binding.winBattleGroup.setVisibility(View.GONE);
                binding.awardHdr.setText("Монстров осталось: " + Integer.toString(gameViewModel.getEnemiesLeft()));
                binding.button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gameViewModel.setIsBattle(false);
                        getDialog().dismiss();
                    }
                });
                break;
            case WIN_ADVENTURE:
                binding.awardCountTxt.setText(Integer.toString(gameViewModel.setAdventureReward()));
                binding.button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gameViewModel.setIsBattle(false);
                        getDialog().dismiss();
                    }
                });
                break;
        }
        return builder.create();
    }
}
