package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.model.UserTask;

// Получение задач с заданным цветом
public class GetTasksWithColorUseCase {

    private UserTaskRepository userTaskRepository;

    public GetTasksWithColorUseCase(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    public ArrayList<UserTask> execute(ArrayList<UserTask> tasks, int color) {
        ArrayList<UserTask> filteredTasks = new ArrayList<>();
        for (UserTask userTask : tasks) {
            if (userTask.getColor() == color) {
                filteredTasks.add(userTask);
            }
        }
        return filteredTasks;
    }
}
