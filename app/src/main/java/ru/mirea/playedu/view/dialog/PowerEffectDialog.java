package ru.mirea.playedu.view.dialog;

import static ru.mirea.playedu.Constants.EFFECT_INFO;

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
import androidx.lifecycle.ViewModelProviders;

import ru.mirea.playedu.Constants.PowerStatus;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.DialogPowerBinding;
import ru.mirea.playedu.databinding.DialogPowerEffectBinding;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.viewmodel.GameViewModel;


// Диалог с информацией о силе
public class PowerEffectDialog extends DialogFragment {

    public interface OnPowerBoughtListener {

        void OnPowerBought(Power power);

    }

    private Power power;
    private final int TYPE_EFFECT;
    private OnPowerBoughtListener boughtListener;

    public PowerEffectDialog(Power power, int type) {
        this.power = power;
        TYPE_EFFECT = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_power_effect,
                null,
                false);

        // Фон диалога
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        DialogPowerEffectBinding binding = DialogPowerEffectBinding.inflate(getLayoutInflater());
        binding.setPower(power);

        GameViewModel gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        // Установка иконки для силы
        binding.achvmntImg.setImageResource(power.getIcon());
        if (TYPE_EFFECT == EFFECT_INFO) {
            binding.useBtn.setVisibility(View.GONE);
        }
        else {
            binding.useBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameViewModel.disablePower(power);
                    gameViewModel.useActivePower(power.getPowerType());
                    getDialog().dismiss();
                }
            });
        }
        builder.setView(binding.getRoot());
        return builder.create();
    }
}
