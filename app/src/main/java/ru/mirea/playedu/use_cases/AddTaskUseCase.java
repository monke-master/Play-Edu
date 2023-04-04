package ru.mirea.playedu.use_cases;

import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.model.UserTask;

public class AddTaskUseCase {

    UserTaskRepository userTaskRepository;

    public AddTaskUseCase(UserTaskRepository repository) {
        userTaskRepository = repository;
    }

    public void execute(UserTask task) {
        userTaskRepository.addTask(task);
    }
}
