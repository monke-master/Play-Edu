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

    private static AchievementCacheStorage getInstance() {
        if (instance == null)
            instance = new AchievementCacheStorage();
        return instance;
    }


    // Получение всех достижений
    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    // Добавление достижения
    public void addTask(Achievement achievement) {
        achievements.add(achievement);
    }

    // Редактирование достижения
    public boolean updateAchievement(int achievementId, Achievement newAchievement) {
        for (int i = 0; i < achievements.size(); i++) {
            if (achievements.get(i).getAchievementId() == achievementId) {
                achievements.set(i, newAchievement);
                return true;
            }
        }
        return false;
    }

    // Удаление достижения
    public boolean deleteAchievement(Achievement achievement) {
        return achievements.remove(achievement);
    }

}
