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

import ru.mirea.playedu.Constants;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.DialogActivePowerBinding;
import ru.mirea.playedu.databinding.DialogDamageBinding;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.viewmodel.GameViewModel;

// Диалог для отображения нанесенного урона
public class ActivePowerDialog extends DialogFragment {

    private GameViewModel gameViewModel;
    private Power power;
    private boolean btnClicked = false;

    public ActivePowerDialog(Power power) {
        this.power = power;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_active_power,
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
        DialogActivePowerBinding binding = DialogActivePowerBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot());
        // Инициализация ViewModel
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        binding.powerImg.setImageResource(power.getIcon());
        switch (power.getPowerType()) {
            case FIRE_POWER:
                binding.damageTxt.setText("1");
                break;
            case ICE_POWER:
                binding.damageGroup.setVisibility(View.GONE);
                binding.hdr.setText(R.string.no_damage_ice_power);
                break;
            case TIME_POWER:
                binding.damageGroup.setVisibility(View.GONE);
                binding.hdr.setText(R.string.mistake_title);
                break;
            case HEALTH_POWER:
                binding.damageGroup.setVisibility(View.GONE);
                binding.hdr.setText(R.string.health_title);
                break;
            case GRIFFIN_POWER:
                binding.damageGroup.setVisibility(View.GONE);
                binding.hdr.setText(R.string.griffin_power_title);
                break;
            case LIFE_POWER:
                binding.damageGroup.setVisibility(View.GONE);
                binding.hdr.setText(R.string.life_power_title);
                break;
        }



        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClicked = true;
                getDialog().dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (btnClicked) {
            switch (power.getPowerType()) {
                case SPELL_BOOK_POWER:
                    gameViewModel.disablePower(power);
                    break;
                case ICE_POWER:
                    gameViewModel.setIsAttack(true);
                    break;
                case LIFE_POWER:
                    gameViewModel.disablePower(power);
                    gameViewModel.setIsAttack(!gameViewModel.getIsAttack().getValue());
                    break;
                case TIME_POWER:
                    gameViewModel.setIsAttack(!gameViewModel.getIsAttack().getValue());
                    break;
                case GRIFFIN_POWER:
                    gameViewModel.disablePower(power);
                    if (gameViewModel.isAllEnemyKilled())
                        gameViewModel.setBattleResult(Constants.BattleResult.WIN_ADVENTURE);
                    else gameViewModel.setBattleResult(Constants.BattleResult.WIN_BATTLE);
                    FightEndDialog fightEndDialog = new FightEndDialog();
                    fightEndDialog.show(getActivity().getSupportFragmentManager(), "Fight end dialog");
                    break;
            }
        }
    }
}
