package ru.mirea.playedu.viewmodel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.playedu.model.User;
import ru.mirea.playedu.usecases.BuyPowerUseCase;
import ru.mirea.playedu.usecases.GetBoughtPowersUseCase;
import ru.mirea.playedu.usecases.GetLockedAchievementsUseCase;
import ru.mirea.playedu.usecases.GetSellingPowersUseCase;
import ru.mirea.playedu.usecases.GetUnlockedAchievementsUseCase;
import ru.mirea.playedu.usecases.GetUserUseCase;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<User> user;
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
        user.setValue(getUserUseCase.execute());

    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void setUser(MutableLiveData<User> user) {
        this.user = user;
    }

}
