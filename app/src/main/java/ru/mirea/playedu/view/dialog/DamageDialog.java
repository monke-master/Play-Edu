package ru.mirea.playedu.view.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.playedu.Constants.Powers;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.DialogDamageBinding;
import ru.mirea.playedu.viewmodel.GameViewModel;

// Диалог для отображения нанесенного урона
public class DamageDialog extends DialogFragment {

    private GameViewModel gameViewModel;

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
    @SuppressLint("DialogFragmentCallbacksDetector")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Получение binding и установка view для диалога
        DialogDamageBinding binding = DialogDamageBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot());
        // Инициализация ViewModel
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        switch (gameViewModel.getCurrentPhaseResult()) {
            case DEAL_DAMAGE:
                binding.damageTxt.setText(Integer.toString(gameViewModel.getPlayer().getDamage()));
                binding.enemyImg.setImageResource(gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getImageId());
                break;
            case MISHIT:
                binding.enemyImg.setImageResource(gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getImageId());
                binding.damageGroup.setVisibility(View.GONE);
                binding.hdr.setText(R.string.no_damage);
                gameViewModel.setMishitPenalty();
                break;
            case AVOID_DAMAGE:
                binding.enemyImg.setImageResource(gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getImageId());
                binding.damageGroup.setVisibility(View.GONE);
                binding.hdr.setText(R.string.no_damage_to_player);
                gameViewModel.useActivePower(Powers.FIRE_POWER);
                break;
            case GET_DAMAGE:
                binding.damageTxt.setText(Integer.toString(gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getDamage()));
                binding.enemyImg.setImageResource(gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getImageId());
                gameViewModel.useActivePower(Powers.FIRE_POWER);
                break;
            case POISON_POWER:
                binding.enemyImg.setImageResource(R.drawable.poison_power_ic);
                binding.damageGroup.setVisibility(View.GONE);
                binding.hdr.setText(R.string.no_damage_poison_power);
                break;
        }



        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (gameViewModel.getIsAttack().getValue()) gameViewModel.setIsAttack(false);
        else gameViewModel.setIsAttack(true);
    }
}
