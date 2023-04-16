package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.FragmentProfileBinding;
import ru.mirea.playedu.model.Achievement;
import ru.mirea.playedu.view.adapter.AchievementAdapter;
import ru.mirea.playedu.view.adapter.PowerAdapter;
import ru.mirea.playedu.view.dialog.AchievementDialog;
import ru.mirea.playedu.view.dialog.PowerDialog;
import ru.mirea.playedu.viewmodel.ProfileViewModel;
import ru.mirea.playedu.viewmodel.ProfileViewModelFabric;

public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentProfileBinding binding = FragmentProfileBinding.inflate(getLayoutInflater());

        ProfileViewModel viewModel = ViewModelProviders.of(this,
                new ProfileViewModelFabric()).get(ProfileViewModel.class);
        binding.setViewModel(viewModel);

        // Табличный RecyclerView для списка сил
        RecyclerView powersList = binding.powersList;
        powersList.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        String[] powers = new String[]{
                "Сила Сибири",
                "Сила Булеана",
                "Сила Воды",
                "Сила Белой профессуры",
                "Сила Беркова",
                "Сила Немировской",
                "Сила Пропастина",
                "Сила Куджа Кобейна",
                "Сила Сибири",
                "Сила Булеана",
                "Сила Воды",
                "Сила Белой профессуры",
                "Сила Беркова",
                "Сила Немировской",
                "Сила Пропастина",
                "Сила Куджа Кобейна",
                "Сила Сибири",
                "Сила Булеана",
                "Сила Воды",
                "Сила Белой профессуры",
                "Сила Беркова",
                "Сила Немировской",
                "Сила Пропастина",
                "Сила Куджа Кобейна",
                "Сила Сибири",
                "Сила Булеана",
                "Сила Воды",
                "Сила Белой профессуры",
                "Сила Беркова",
                "Сила Немировской",
                "Сила Пропастина",
                "Сила Куджа Кобейна",
        };
        powersList.setNestedScrollingEnabled(false);
        powersList.setAdapter(new PowerAdapter(powers, new PowerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name) {
                PowerDialog dialog = new PowerDialog(name);
                dialog.show(getActivity().getSupportFragmentManager(), "Power dialog");
            }
        }));


        // Список достижений
        // Сначала идут уже полученные игроком достижения, затем оставшиеся
        ArrayList<Achievement> achievements = viewModel.getUnlockedAchievements().getValue();
        achievements.addAll(viewModel.getLockedAchievements().getValue());
        // Табличный RecyclerView для списка достижений
        RecyclerView achievementsList = binding.achvmntsList;
        achievementsList.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        achievementsList.setNestedScrollingEnabled(false);

        // Отслеживание нажатия на элемент списка
        achievementsList.setAdapter(new AchievementAdapter(achievements, achievement -> {
            AchievementDialog dialog = new AchievementDialog(achievement);
            dialog.show(getActivity().getSupportFragmentManager(), "Achievement dialog");
        }));

        return binding.getRoot();
    }
}