package ru.mirea.playedu.data.storage.cache;

import java.util.ArrayList;

import ru.mirea.playedu.model.Achievement;
import ru.mirea.playedu.model.UserTask;

// Класс для хранения пользовательских задач в кэше
// Реализует паттерн Singleton
public class UserTaskCacheStorage {

    // Список задач
    private ArrayList<UserTask> tasks;

    private static UserTaskCacheStorage instance = null;

    private UserTaskCacheStorage() {
        tasks = new ArrayList<>();
    }


    public static UserTaskCacheStorage getInstance() {
        if (instance == null)
            instance = new UserTaskCacheStorage();

        return instance;
    }

    // Возвращает задачу с заданным id, если она есть в списке
    // Иначе возвращает null
    public UserTask getTaskById(int id) {
        for (UserTask task: tasks) {
            if (task.getTaskId() == id)
                return task;
        }
        return null;
    }

    // Возвращает список задач
    public ArrayList<UserTask> getTasks() {
        return tasks;
    }

    // Добавляет задачу
    public void addTask(UserTask task) {
        tasks.add(task);
    }

    // Заменяет задачу с заданным id на новую
    public boolean updateTask(int taskId, UserTask newTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                tasks.set(i, newTask);
                return true;
            }
        }
        return false;
    }

    // Удаляет задачу с заданным id
    public boolean deleteTask(UserTask task) {
        return tasks.remove(task);
    }

}
