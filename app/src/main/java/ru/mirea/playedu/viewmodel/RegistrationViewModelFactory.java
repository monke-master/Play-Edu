package ru.mirea.playedu.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.data.repository.UserStatsRepository;
import ru.mirea.playedu.data.storage.cache.UserCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserStatsCacheStorage;
import ru.mirea.playedu.usecases.SignUpUseCase;

// Фабрика для класса RegistrationViewModel
public class RegistrationViewModelFactory implements ViewModelProvider.Factory {

    private SignUpUseCase signUpUseCase;

    private static RegistrationViewModel instance = null;

    public RegistrationViewModelFactory() {
        UserRepository userRepository = new UserRepository(UserCacheStorage.getInstance());
        UserStatsRepository userStatsRepository = new UserStatsRepository(UserStatsCacheStorage.getInstance());
        signUpUseCase = new SignUpUseCase(userRepository, userStatsRepository);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (instance == null)
            instance = new RegistrationViewModel(signUpUseCase);
        return (T) instance;
    }
}
