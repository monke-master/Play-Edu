package ru.mirea.playedu.viewmodel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

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
    private GetUserUseCase getUserUseCase;
    private GetBoughtPowersUseCase getBoughtPowersUseCase;
    private GetSellingPowersUseCase getSellingPowersUseCase;
    private GetLockedAchievementsUseCase getLockedAchievementsUseCase;
    private GetUnlockedAchievementsUseCase getUnlockedAchievementsUseCase;
    private BuyPowerUseCase buyPowerUseCase;

    public ProfileViewModel(GetUserUseCase getUserUseCase,
                            GetBoughtPowersUseCase getBoughtPowersUseCase,
                            GetSellingPowersUseCase getSellingPowersUseCase,
                            GetLockedAchievementsUseCase getLockedAchievementsUseCase,
                            GetUnlockedAchievementsUseCase getUnlockedAchievementsUseCase,
                            BuyPowerUseCase buyPowerUseCase) {
        this.getUnlockedAchievementsUseCase = getUnlockedAchievementsUseCase;
        this.getUserUseCase = getUserUseCase;
        this.getBoughtPowersUseCase = getBoughtPowersUseCase;
        this.buyPowerUseCase = buyPowerUseCase;
        this.getLockedAchievementsUseCase = getLockedAchievementsUseCase;
        this.getSellingPowersUseCase = getSellingPowersUseCase;

        user = new MutableLiveData<>();
        boughtPowers = new MutableLiveData<>();
        sellingPowers = new MutableLiveData<>();
        lockedAchievements = new MutableLiveData<>();
        unlockedAchievements = new MutableLiveData<>();
        // Получение данных из репозиториев
        getData();
    }

    // Покупка силы
    // Возвращает код
    public int buyPower(Power power) {
        Response response = buyPowerUseCase.execute(power);
        // В случае успеха изменяет списки сил и пользователя
        if (response.getCode() == 200) {
            sellingPowers.getValue().remove(power);
            power.setBought(true);
            boughtPowers.getValue().add(power);
            user.setValue((User)response.getResponseObject());
        }
        return response.getCode();
    }

    // Получение данных из репозиториев
    private void getData() {
        user.setValue(getUserUseCase.execute());
        boughtPowers.setValue(getBoughtPowersUseCase.execute());
        sellingPowers.setValue(getSellingPowersUseCase.execute());
        lockedAchievements.setValue(getLockedAchievementsUseCase.execute());
        unlockedAchievements.setValue(getUnlockedAchievementsUseCase.execute());
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

    public void setBoughtPowers(MutableLiveData<ArrayList<Power>> boughtPowers) {
        this.boughtPowers = boughtPowers;
    }

    public MutableLiveData<ArrayList<Power>> getSellingPowers() {
        return sellingPowers;
    }

    public void setSellingPowers(MutableLiveData<ArrayList<Power>> sellingPowers) {
        this.sellingPowers = sellingPowers;
    }

    public MutableLiveData<ArrayList<Achievement>> getUnlockedAchievements() {
        return unlockedAchievements;
    }

    public void setUnlockedAchievements(MutableLiveData<ArrayList<Achievement>> unlockedAchievements) {
        this.unlockedAchievements = unlockedAchievements;
    }

    public MutableLiveData<ArrayList<Achievement>> getLockedAchievements() {
        return lockedAchievements;
    }

    public void setLockedAchievements(MutableLiveData<ArrayList<Achievement>> lockedAchievements) {
        this.lockedAchievements = lockedAchievements;
    }
}
