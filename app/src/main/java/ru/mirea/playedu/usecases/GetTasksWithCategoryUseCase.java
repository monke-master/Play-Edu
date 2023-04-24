package ru.mirea.playedu.usecases;

import java.util.ArrayList;
import java.util.Objects;

import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.model.Category;
import ru.mirea.playedu.model.UserTask;

// Получение задач с заданной категорией
public class GetTasksWithCategoryUseCase {

    private UserTaskRepository userTaskRepository;

    public GetTasksWithCategoryUseCase(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    public ArrayList<UserTask> execute(ArrayList<UserTask> tasks, String categoryTitle) {
        ArrayList<UserTask> filteredTasks = new ArrayList<>();
        for (UserTask userTask: tasks) {
            if (categoryTitle.equals(userTask.getCategory().getTitle())) {
                filteredTasks.add(userTask);
            }
        }
        return filteredTasks;
    }
}
