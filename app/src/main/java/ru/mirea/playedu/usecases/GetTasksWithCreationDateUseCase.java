package ru.mirea.playedu.usecases;

import java.util.ArrayList;
import java.util.Calendar;

import ru.mirea.playedu.DateHelper;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.model.UserTask;

// Получение задач с заданной датой создания
public class GetTasksWithCreationDateUseCase {

    private UserTaskRepository userTaskRepository;

    public GetTasksWithCreationDateUseCase(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    public ArrayList<UserTask> execute(ArrayList<UserTask> tasks, Calendar date) {
        ArrayList<UserTask> filteredTasks = new ArrayList<>();
        for (UserTask userTask : tasks) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(userTask.getCreationDate());
            if (DateHelper.equalDate(date, calendar)) {
                filteredTasks.add(userTask);
            }
        }
        return filteredTasks;
    }
}
