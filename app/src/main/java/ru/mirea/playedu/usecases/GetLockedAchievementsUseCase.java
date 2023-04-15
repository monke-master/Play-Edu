package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.AchievementRepository;
import ru.mirea.playedu.model.Achievement;

// UseCase получения списка закрытых достижений
public class GetLockedAchievementsUseCase {

    private AchievementRepository achievementRepository;

    public GetLockedAchievementsUseCase(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public ArrayList<Achievement> execute() {
        ArrayList<Achievement> achievements = achievementRepository.getAchievements();
        ArrayList<Achievement> lockedAchievements = new ArrayList<>();
        for (Achievement achievement: achievements) {
            if (!achievement.isUnlocked()) {
                lockedAchievements.add(achievement);
            }
        }
        return lockedAchievements;
    }
}
