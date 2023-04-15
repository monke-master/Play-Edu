package ru.mirea.playedu.data.storage.cache;

import java.util.ArrayList;

import ru.mirea.playedu.model.Achievement;

// Класс для хранения достижений пользователя в кэше
// Реализует паттерн Singleton
public class AchievementCacheStorage {

    // Список достижений
    private ArrayList<Achievement> achievements;

    private static AchievementCacheStorage instance = null;

    private AchievementCacheStorage() {
        achievements = new ArrayList<>();
    }

    // Возвращает экземпляр класса
    // Если экземпляра нет, создает новый
    public static AchievementCacheStorage getInstance() {
        if (instance == null)
            instance = new AchievementCacheStorage();
        return instance;
    }

    // Возвращает достижение с заданным id, если она есть в списке
    // Иначе возвращает null
    public Achievement getAchievementById(int id) {
        for (Achievement achievement: achievements) {
            if (achievement.getAchievementId() == id)
                return achievement;
        }
        return null;
    }

    // Возвращает список достижений пользователя
    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    // Добавляет достижение в список
    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    // Редактирует достижение по заданному id
    public boolean updateAchievement(int achievementId, Achievement newAchievement) {
        for (int i = 0; i < achievements.size(); i++) {
            if (achievements.get(i).getAchievementId() == achievementId) {
                achievements.set(i, newAchievement);
                return true;
            }
        }
        return false;
    }

    // Удаляет достижение по заданному id
    public boolean deleteAchievement(Achievement achievement) {
        return achievements.remove(achievement);
    }

}
