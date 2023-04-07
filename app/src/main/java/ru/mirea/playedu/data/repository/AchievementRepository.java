package ru.mirea.playedu.data.repository;


import java.util.ArrayList;

import ru.mirea.playedu.data.storage.cache.AchievementCacheStorage;
import ru.mirea.playedu.model.Achievement;
import ru.mirea.playedu.model.RepositoryResponse;

// Класс-репозиторий для достижений игрока
public class AchievementRepository {

    private final AchievementCacheStorage cacheStorage;

    public AchievementRepository(AchievementCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    // Возвращает достижение с заданным id, если она есть в списке
    // Иначе возвращает null
    public Achievement getAchievementById(int id) {
        return cacheStorage.getAchievementById(id);
    }

    // Возвращает список достижений пользователя
    public ArrayList<Achievement> getAchievements() {
        return cacheStorage.getAchievements();
    }

    // Добавляет достижение
    public RepositoryResponse addAchievement(Achievement achievement) {
        cacheStorage.addAchievement(achievement);
        return new RepositoryResponse(200, "Success");
    }

    // Редактирует достижение по заданному id
    public RepositoryResponse deleteAchievement(Achievement achievement) {
        if (cacheStorage.deleteAchievement(achievement)) {
            return new RepositoryResponse(200, "Success");
        } else {
            return new RepositoryResponse(404, "Not found");
        }
    }

    // Удаляет достижение по заданному id
    public RepositoryResponse updateAchievement(int id, Achievement newAchievement) {
        if (cacheStorage.updateAchievement(id, newAchievement)) {
            return new RepositoryResponse(200, "Success");
        } else {
            return new RepositoryResponse(404, "Not found");
        }
    }


}
