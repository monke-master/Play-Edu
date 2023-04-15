package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.AchievementRepository;
import ru.mirea.playedu.model.Achievement;

// UseCase получения списка полученных игроком достижений
public class GetUnlockedAchievementsUseCase {

    private AchievementRepository achievementRepository;

    public GetUnlockedAchievementsUseCase(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public ArrayList<Achievement> execute() {
        ArrayList<Achievement> achievements = achievementRepository.getAchievements();
        ArrayList<Achievement> unlockedAchievements = new ArrayList<>();
        for (Achievement achievement: achievements) {
            if (achievement.isUnlocked()) {
                unlockedAchievements.add(achievement);
            }
        }
        return unlockedAchievements;
    }
}
