package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.model.UserTask;

// Получение списка активных пользовательских задач
public class GetUserTasksListUseCase {

    private UserTaskRepository userTaskRepository;

    public GetUserTasksListUseCase(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    public ArrayList<UserTask> execute() {
        ArrayList<UserTask> userTasks = userTaskRepository.getTasks();
        ArrayList<UserTask> activeTasks = new ArrayList<>();
        for (UserTask task: userTasks) {
            if (!task.isCompleted())
                activeTasks.add(task);
        }
        return activeTasks;
    }
}
