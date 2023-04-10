package ru.mirea.playedu.data.repository;

import ru.mirea.playedu.data.storage.cache.UserStatsCacheStorage;
import ru.mirea.playedu.model.Response;
import ru.mirea.playedu.model.UserStats;

// Репозиторий для хранения пользовательской статистики
public class UserStatsRepository {

    private UserStatsCacheStorage cacheStorage;

    public UserStatsRepository(UserStatsCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }


    public UserStats getUserStats() {
        return cacheStorage.getUserStats();
    }

    public Response setUserStats(UserStats newStats) {
        cacheStorage.setUserStats(newStats);
        return new Response(200, "Success");
    }
}
