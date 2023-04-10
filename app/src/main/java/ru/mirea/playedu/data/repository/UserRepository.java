package ru.mirea.playedu.data.repository;

import ru.mirea.playedu.model.Response;
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

    // Создает пользователя
    // В случае успешного создания возвращает id новой учетной записи
    public Response createUser(User user) {
        cacheStorage.setUser(user);
        Response repositoryResponse = new Response(200, "Success");
        repositoryResponse.setResponseObject(1);
        return repositoryResponse;
    }

    public Response updateUser(User user) {
        cacheStorage.setUser(user);
        return new Response(200, "Success");
    }
}
