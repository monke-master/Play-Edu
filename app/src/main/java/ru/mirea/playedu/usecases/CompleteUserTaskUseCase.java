package ru.mirea.playedu.usecases;

import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.data.repository.UserStatsRepository;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.model.Response;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.model.UserStats;
import ru.mirea.playedu.model.UserTask;

// Устанавливает указанному пользовательскому заданию статус "выполнено"
// Редактирует статистику пользователя
public class CompleteUserTaskUseCase {

    private UserTaskRepository userTaskRepository;
    private UserStatsRepository userStatsRepository;
    private UserRepository userRepository;


    public CompleteUserTaskUseCase(UserTaskRepository userTaskRepository,
                                   UserStatsRepository userStatsRepository,
                                   UserRepository userRepository) {
        this.userTaskRepository = userTaskRepository;
        this.userStatsRepository = userStatsRepository;
        this.userRepository = userRepository;
    }

    public Response execute(UserTask userTask) {
        userTask.setCompleted(true);
        Response taskResponse = userTaskRepository.updateTask(userTask.getTaskId(), userTask);
        if (taskResponse.getCode() != 200)
            return taskResponse;

        UserStats userStats = userStatsRepository.getUserStats();
        userStats.setUserTasksCompleted(userStats.getUserTasksCompleted() + 1);
        Response statsResponse = userStatsRepository.setUserStats(userStats);
        if (statsResponse.getCode() != 200)
            return statsResponse;

        User user = userRepository.getUser();
        user.setSilverCoins(user.getSilverCoins() + userTask.getCoinsReward());
        return userRepository.updateUser(user);
    }
}
