package ru.mirea.playedu.data.storage.cache;

import ru.mirea.playedu.model.User;

// Класс для хранения объекта-пользователя в кэше
// Реализует паттерн Singleton
public class UserCacheStorage {

    private static UserCacheStorage instance = null;

    private User user;

    private UserCacheStorage() {}

    // Возвращает экземпляр класса
    // Если экземпляра нет, создает новый
    public static UserCacheStorage getInstance() {
        if (instance == null)
            instance = new UserCacheStorage();

        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
