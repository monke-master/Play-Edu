package ru.mirea.playedu.usecases;

import ru.mirea.playedu.data.repository.UserStatsRepository;
import ru.mirea.playedu.model.UserStats;

// Use-case для получения статистики пользователя
public class GetUserStatsUseCase {

    private UserStatsRepository statsRepository;

    public GetUserStatsUseCase(UserStatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public UserStats execute() {
        return statsRepository.getUserStats();
    }
}
