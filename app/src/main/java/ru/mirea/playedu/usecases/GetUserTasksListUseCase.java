package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.model.UserTask;

// Получение списка пользовательских задач
public class GetUserTasksListUseCase {

    private UserTaskRepository userTaskRepository;

    public GetUserTasksListUseCase(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    public ArrayList<UserTask> execute() {
        return userTaskRepository.getTasks();
    }
}
