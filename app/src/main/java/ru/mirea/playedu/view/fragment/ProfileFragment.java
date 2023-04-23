package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.FragmentProfileBinding;
import ru.mirea.playedu.model.Achievement;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.view.adapter.AchievementAdapter;
import ru.mirea.playedu.view.adapter.PowerAdapter;
import ru.mirea.playedu.view.dialog.AchievementDialog;
import ru.mirea.playedu.view.dialog.PowerDialog;
import ru.mirea.playedu.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment {


    private ProfileViewModel viewModel;
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

//        viewModel = ViewModelProviders.of(this,
//                new ProfileViewModelFabric()).get(ProfileViewModel.class);
        viewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        binding.setViewModel(viewModel);

        // Табличный RecyclerView для списка сил
        RecyclerView powersList = binding.powersList;
        powersList.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        powersList.setNestedScrollingEnabled(false);

        // Создание адаптера
        // В аргументы передается список сил и callback для отслеживания нажатия на силу
        PowerAdapter powerAdapter = new PowerAdapter(viewModel.getPowersList(), (power, position) -> {
            PowerDialog dialog = new PowerDialog(power);
            dialog.show(getActivity().getSupportFragmentManager(), "Power dialog");
        });
        powersList.setAdapter(powerAdapter);

        // Табличный RecyclerView для списка достижений
        RecyclerView achievementsList = binding.achvmntsList;
        achievementsList.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        achievementsList.setNestedScrollingEnabled(false);

        // Создание адаптера
        // В аргументы передается список сил и callback для отслеживания нажатия на ачивку
        AchievementAdapter achievementAdapter = new AchievementAdapter(viewModel.getAchievementsList(), achievement -> {
            AchievementDialog dialog = new AchievementDialog(achievement);
            dialog.show(getActivity().getSupportFragmentManager(), "Achievement dialog");
        });
        achievementsList.setAdapter(achievementAdapter);

        viewModel.getBoughtPowers().observe(getViewLifecycleOwner(), powers ->
            powerAdapter.setPowersList(viewModel.getPowersList())
        );

        viewModel.getUnlockedAchievements().observe(getViewLifecycleOwner(), achievements ->
            achievementAdapter.setAchievementsList(viewModel.getAchievementsList())
        );

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        });

        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            binding.silverCountTxt.setText(Integer.toString(user.getSilverCoins()));
        });

        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            binding.goldenCountTxt.setText(Integer.toString(user.getGoldenCoins()));
        });


        return binding.getRoot();
    }

}