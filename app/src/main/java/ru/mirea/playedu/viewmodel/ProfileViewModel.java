package ru.mirea.playedu.viewmodel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.AchievementRepository;
import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.data.storage.cache.AchievementCacheStorage;
import ru.mirea.playedu.data.storage.cache.PowerCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserCacheStorage;
import ru.mirea.playedu.model.Achievement;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.Response;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.usecases.BuyPowerUseCase;
import ru.mirea.playedu.usecases.GetBoughtPowersUseCase;
import ru.mirea.playedu.usecases.GetLockedAchievementsUseCase;
import ru.mirea.playedu.usecases.GetSellingPowersUseCase;
import ru.mirea.playedu.usecases.GetUnlockedAchievementsUseCase;
import ru.mirea.playedu.usecases.GetUserUseCase;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<User> user;
    private MutableLiveData<ArrayList<Power>> boughtPowers;
    private MutableLiveData<ArrayList<Power>> sellingPowers;
    private MutableLiveData<ArrayList<Achievement>> unlockedAchievements;
    private MutableLiveData<ArrayList<Achievement>> lockedAchievements;
    private MutableLiveData<String> errorMessage;
    private GetUserUseCase getUserUseCase;
    private GetBoughtPowersUseCase getBoughtPowersUseCase;
    private GetSellingPowersUseCase getSellingPowersUseCase;
    private GetLockedAchievementsUseCase getLockedAchievementsUseCase;
    private GetUnlockedAchievementsUseCase getUnlockedAchievementsUseCase;
    private BuyPowerUseCase buyPowerUseCase;

//    public ProfileViewModel(GetUserUseCase getUserUseCase,
//                            GetBoughtPowersUseCase getBoughtPowersUseCase,
//                            GetSellingPowersUseCase getSellingPowersUseCase,
//                            GetLockedAchievementsUseCase getLockedAchievementsUseCase,
//                            GetUnlockedAchievementsUseCase getUnlockedAchievementsUseCase,
//                            BuyPowerUseCase buyPowerUseCase) {
//        this.getUnlockedAchievementsUseCase = getUnlockedAchievementsUseCase;
//        this.getUserUseCase = getUserUseCase;
//        this.getBoughtPowersUseCase = getBoughtPowersUseCase;
//        this.buyPowerUseCase = buyPowerUseCase;
//        this.getLockedAchievementsUseCase = getLockedAchievementsUseCase;
//        this.getSellingPowersUseCase = getSellingPowersUseCase;
//
//        user = new MutableLiveData<>();
//        boughtPowers = new MutableLiveData<>();
//        sellingPowers = new MutableLiveData<>();
//        lockedAchievements = new MutableLiveData<>();
//        unlockedAchievements = new MutableLiveData<>();
//        errorMessage = new MutableLiveData<>();
//        // Получение данных из репозиториев
//        getData();
//    }

    public ProfileViewModel() {
        // Инициализация репозиториев
        UserRepository userRepository = new UserRepository(UserCacheStorage.getInstance());
        PowerRepository powerRepository = new PowerRepository(PowerCacheStorage.getInstance());
        AchievementRepository achievementRepository = new AchievementRepository(AchievementCacheStorage.getInstance());

        // Инициализация юз-кейсов
        getUserUseCase = new GetUserUseCase(userRepository);
        getBoughtPowersUseCase = new GetBoughtPowersUseCase(powerRepository);
        getSellingPowersUseCase = new GetSellingPowersUseCase(powerRepository);
        getUnlockedAchievementsUseCase = new GetUnlockedAchievementsUseCase(achievementRepository);
        getLockedAchievementsUseCase = new GetLockedAchievementsUseCase(achievementRepository);
        buyPowerUseCase = new BuyPowerUseCase(powerRepository, userRepository);

        user = new MutableLiveData<>();
        boughtPowers = new MutableLiveData<>();
        sellingPowers = new MutableLiveData<>();
        lockedAchievements = new MutableLiveData<>();
        unlockedAchievements = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        // Получение данных из репозиториев
        getData();
    }

    // Покупка силы
    // Возвращает код
    public void buyPower(Power power) {
        Response response = buyPowerUseCase.execute(power);
        // В случае успеха изменяет списки сил и пользователя
        if (response.getCode() == 200) {
            sellingPowers.getValue().remove(power);
            power.setBought(true);
            boughtPowers.getValue().add(power);
            boughtPowers.setValue(boughtPowers.getValue());
            user.setValue((User)response.getResponseObject());
        }
    }

    // Получение данных из репозиториев
    private void getData() {
        user.setValue(getUserUseCase.execute());
        boughtPowers.setValue(getBoughtPowersUseCase.execute());
        sellingPowers.setValue(getSellingPowersUseCase.execute());
        lockedAchievements.setValue(getLockedAchievementsUseCase.execute());
        unlockedAchievements.setValue(getUnlockedAchievementsUseCase.execute());
    }

    public ArrayList<Power> getPowersList() {
        ArrayList<Power> powers = new ArrayList<>();
        powers.addAll(boughtPowers.getValue());
        powers.addAll(sellingPowers.getValue());
        return powers;
    }

    public ArrayList<Achievement> getAchievementsList() {
        ArrayList<Achievement> achievements = new ArrayList<>();
        achievements.addAll(unlockedAchievements.getValue());
        achievements.addAll(lockedAchievements.getValue());
        return achievements;
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void setUser(MutableLiveData<User> user) {
        this.user = user;
    }

    public MutableLiveData<ArrayList<Power>> getBoughtPowers() {
        return boughtPowers;
    }


    public MutableLiveData<ArrayList<Achievement>> getUnlockedAchievements() {
        return unlockedAchievements;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

}
