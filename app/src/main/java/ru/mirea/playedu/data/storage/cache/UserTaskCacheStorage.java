package ru.mirea.playedu.data.storage.cache;

import java.util.ArrayList;

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


    // Получение всех задач
    public ArrayList<UserTask> getTasks() {
        return tasks;
    }

    // Добавление задачи
    public void addTask(UserTask task) {
        tasks.add(task);
    }

    // Редактирование задачи
    public boolean updateTask(int taskId, UserTask newTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                tasks.set(i, newTask);
                return true;
            }
        }
        return false;
    }

    // Удаление задачи
    public boolean deleteTask(UserTask task) {
        return tasks.remove(task);
    }

}
