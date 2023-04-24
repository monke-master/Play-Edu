package ru.mirea.playedu.usecases;

import ru.mirea.playedu.data.repository.UserStatsRepository;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.model.Response;
import ru.mirea.playedu.model.UserStats;
import ru.mirea.playedu.model.UserTask;

// Устанавливает указанному пользовательскому заданию статус "выполнено"
// Редактирует статистику пользователя
public class CompleteUserTaskUseCase {

    private UserTaskRepository userTaskRepository;
    private UserStatsRepository userStatsRepository;


    public CompleteUserTaskUseCase(UserTaskRepository userTaskRepository, UserStatsRepository userStatsRepository) {
        this.userTaskRepository = userTaskRepository;
        this.userStatsRepository = userStatsRepository;
    }

    public Response execute(UserTask userTask) {
        userTask.setCompleted(true);
        UserStats userStats = userStatsRepository.getUserStats();
        userStats.setUserTasksCompleted(userStats.getUserTasksCompleted() + 1);
        Response taskResponse = userTaskRepository.updateTask(userTask.getTaskId(), userTask);
        if (taskResponse.getCode() != 200)
            return taskResponse;
        return userStatsRepository.setUserStats(userStats);
    }
}
