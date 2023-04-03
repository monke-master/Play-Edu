package ru.mirea.playedu.data.storage.cache;

import ru.mirea.playedu.model.UserStats;

// Класс для хранения пользовательской статистики в кэше
// Реализует паттерн Singleton
public class UserStatsCacheStorage {

    private UserStats userStats;

    private UserStatsCacheStorage instance = null;

    private UserStatsCacheStorage() {}

    // Возвращает экземпляр класса
    // Если экземпляра нет, создает новый
    public UserStatsCacheStorage getInstance() {
        if (instance == null)
            instance = new UserStatsCacheStorage();
        return instance;
    }

    public UserStats getUserStats() {
        return userStats;
    }

    public void setUserStats(UserStats userStats) {
        this.userStats = userStats;
    }

}
