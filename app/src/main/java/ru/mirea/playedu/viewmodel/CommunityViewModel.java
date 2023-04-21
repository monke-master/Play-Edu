package ru.mirea.playedu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.playedu.data.repository.UserStatsRepository;
import ru.mirea.playedu.data.storage.cache.UserStatsCacheStorage;
import ru.mirea.playedu.model.UserStats;
import ru.mirea.playedu.usecases.GetUserStatsUseCase;

public class CommunityViewModel extends ViewModel {

    private GetUserStatsUseCase getUserStatsUseCase;
    private MutableLiveData<UserStats> userStats;

    public CommunityViewModel() {
        this.getUserStatsUseCase = new GetUserStatsUseCase(new
                UserStatsRepository(UserStatsCacheStorage.getInstance()));
        userStats = new MutableLiveData<>();
        userStats.setValue(getUserStatsUseCase.execute());

    }

    public LiveData<UserStats> getUserStats() {
        return userStats;
    }



}
