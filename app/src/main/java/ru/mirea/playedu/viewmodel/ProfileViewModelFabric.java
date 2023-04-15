package ru.mirea.playedu.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.playedu.data.repository.AchievementRepository;
import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.data.storage.cache.AchievementCacheStorage;
import ru.mirea.playedu.data.storage.cache.PowerCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserCacheStorage;
import ru.mirea.playedu.usecases.BuyPowerUseCase;
import ru.mirea.playedu.usecases.GetBoughtPowersUseCase;
import ru.mirea.playedu.usecases.GetLockedAchievementsUseCase;
import ru.mirea.playedu.usecases.GetSellingPowersUseCase;
import ru.mirea.playedu.usecases.GetUnlockedAchievementsUseCase;
import ru.mirea.playedu.usecases.GetUserUseCase;

// Фабрика для ProfileViewModel
public class ProfileViewModelFabric implements ViewModelProvider.Factory {

    private GetUserUseCase getUserUseCase;
    private GetBoughtPowersUseCase getBoughtPowersUseCase;
    private GetSellingPowersUseCase getSellingPowersUseCase;
    private GetLockedAchievementsUseCase getLockedAchievementsUseCase;
    private GetUnlockedAchievementsUseCase getUnlockedAchievementsUseCase;
    private BuyPowerUseCase buyPowerUseCase;

    public ProfileViewModelFabric() {
        getUserUseCase = new GetUserUseCase(new UserRepository(UserCacheStorage.getInstance()));
        getBoughtPowersUseCase = new GetBoughtPowersUseCase(new PowerRepository(PowerCacheStorage.getInstance()));
        getSellingPowersUseCase = new GetSellingPowersUseCase(new PowerRepository(PowerCacheStorage.getInstance()));
        getUnlockedAchievementsUseCase = new GetUnlockedAchievementsUseCase(
                new AchievementRepository(AchievementCacheStorage.getInstance()));
        getLockedAchievementsUseCase = new GetLockedAchievementsUseCase(
                new AchievementRepository(AchievementCacheStorage.getInstance()));
        buyPowerUseCase = new BuyPowerUseCase(
                new PowerRepository(PowerCacheStorage.getInstance()));
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileViewModel(getUserUseCase, getBoughtPowersUseCase, getSellingPowersUseCase,
                getLockedAchievementsUseCase, getUnlockedAchievementsUseCase, buyPowerUseCase);
    }
}
