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
import androidx.lifecycle.ViewModelProviders;

import ru.mirea.playedu.Constants;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.DialogPowerBinding;

import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.viewmodel.ProfileViewModel;


// Диалог с информацией о силе
public class PowerDialog extends DialogFragment {

    private Power power;

    public PowerDialog(Power power) {
        this.power = power;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_power,
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

        DialogPowerBinding binding = DialogPowerBinding.inflate(getLayoutInflater());
        binding.setPower(power);

        //ProfileViewModel viewModel = ViewModelProviders.of(this,
       //         new ProfileViewModelFabric()).get(ProfileViewModel.class);
        ProfileViewModel viewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        User user = viewModel.getUser().getValue();

        // Установка иконки для силы
        binding.achvmntImg.setImageResource(power.getIcon());
        // Меняет кнопку, если сила уже куплена
        if (power.isBought()) {
            binding.buyBtn.setEnabled(false);
            binding.buyBtn.setBackgroundColor(getResources().getColor(R.color.gray));
            binding.buyBtn.setText(R.string.bought);
        }

        // Меняет вид цены и монеты в зависимости от типа силы
        if (power.getPriceType() == Constants.SILVER_COINS_TYPE) {
            binding.priceTxt.setTextColor(getResources().getColor(R.color.silver));
            binding.coinIc.setImageDrawable(getResources().getDrawable(R.drawable.pic_silver_coin));
            if (user.getSilverCoins() < power.getPrice()) {
                binding.buyBtn.setEnabled(false);
                binding.buyBtn.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        } else if (user.getGoldenCoins() < power.getPrice()) {
            binding.buyBtn.setEnabled(false);
            binding.buyBtn.setBackgroundColor(getResources().getColor(R.color.gray));
        }

        binding.buyBtn.setOnClickListener(view -> {
            viewModel.buyPower(power);
            dismiss();
        });

        builder.setView(binding.getRoot());
        return builder.create();
    }
}
