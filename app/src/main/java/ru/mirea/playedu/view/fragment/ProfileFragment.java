package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.FragmentProfileBinding;
import ru.mirea.playedu.view.adapter.PowerAdapter;

public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentProfileBinding binding = FragmentProfileBinding.inflate(getLayoutInflater());

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
        powersList.setAdapter(new PowerAdapter(powers));

        // Табличный RecyclerView для списка достижений
        RecyclerView achievementsList = binding.achvmntsList;
        achievementsList.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        String[] achievements = new String[]{
                "Медаль ВТ",
                "Орден Беркова",
                "Орден Святого Красникова",
                "Знамя пропастинского ударника"
        };
        achievementsList.setNestedScrollingEnabled(false);
        achievementsList.setAdapter(new PowerAdapter(achievements));

        return binding.getRoot();
    }
}