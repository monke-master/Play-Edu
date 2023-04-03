package ru.mirea.playedu.data.repository;

import ru.mirea.playedu.model.RepositoryResponse;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.data.storage.cache.UserCacheStorage;

// Репозиторий для хранения данных пользователя
public class UserRepository {

    private UserCacheStorage cacheStorage;

    public UserRepository(UserCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }


    public User getUser() {
        return cacheStorage.getUser();
    }

    public RepositoryResponse updateUser(User user) {
        cacheStorage.setUser(user);
        return new RepositoryResponse(200, "Success");
    }
}
