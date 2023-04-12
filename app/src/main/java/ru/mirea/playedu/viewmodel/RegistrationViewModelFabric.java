package ru.mirea.playedu.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.data.repository.UserStatsRepository;

public class RegistrationViewModelFabric implements ViewModelProvider.Factory {

    private UserRepository userRepository;
    private UserStatsRepository userStatsRepository;

    public RegistrationViewModelFabric(UserRepository userRepository, UserStatsRepository userStatsRepository) {
        this.userRepository = userRepository;
        this.userStatsRepository = userStatsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
