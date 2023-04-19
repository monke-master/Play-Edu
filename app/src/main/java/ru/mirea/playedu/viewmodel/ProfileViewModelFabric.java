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

//// Фабрика для ProfileViewModel
//public class ProfileViewModelFabric implements ViewModelProvider.Factory {
//
//    private GetUserUseCase getUserUseCase;
//    private GetBoughtPowersUseCase getBoughtPowersUseCase;
//    private GetSellingPowersUseCase getSellingPowersUseCase;
//    private GetLockedAchievementsUseCase getLockedAchievementsUseCase;
//    private GetUnlockedAchievementsUseCase getUnlockedAchievementsUseCase;
//    private BuyPowerUseCase buyPowerUseCase;
//
//    private ProfileViewModel instance = null;
//
//    public ProfileViewModelFabric() {
//        // Инициализация репозиториев
//        UserRepository userRepository = new UserRepository(UserCacheStorage.getInstance());
//        PowerRepository powerRepository = new PowerRepository(PowerCacheStorage.getInstance());
//        AchievementRepository achievementRepository = new AchievementRepository(AchievementCacheStorage.getInstance());
//
//        // Инициализация юз-кейсов
//        getUserUseCase = new GetUserUseCase(userRepository);
//        getBoughtPowersUseCase = new GetBoughtPowersUseCase(powerRepository);
//        getSellingPowersUseCase = new GetSellingPowersUseCase(powerRepository);
//        getUnlockedAchievementsUseCase = new GetUnlockedAchievementsUseCase(achievementRepository);
//        getLockedAchievementsUseCase = new GetLockedAchievementsUseCase(achievementRepository);
//        buyPowerUseCase = new BuyPowerUseCase(powerRepository, userRepository);
//    }
//
//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        if (instance == null)
//            instance = new ProfileViewModel(getUserUseCase, getBoughtPowersUseCase, getSellingPowersUseCase,
//                    getLockedAchievementsUseCase, getUnlockedAchievementsUseCase, buyPowerUseCase);
//        return (T) instance;
//    }
//}
