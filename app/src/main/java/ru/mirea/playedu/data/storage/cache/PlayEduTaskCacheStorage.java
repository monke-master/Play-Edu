package ru.mirea.playedu.data.storage.cache;

import java.util.ArrayList;

import ru.mirea.playedu.model.PlayEduTask;

// Класс для хранения заданий от игры в кэше
// Реализует паттерн Singleton
public class PlayEduTaskCacheStorage {

    private ArrayList<PlayEduTask> tasks;

    private static PlayEduTaskCacheStorage instance = null;

    private PlayEduTaskCacheStorage() {
        tasks = new ArrayList<>();
    }

    private static PlayEduTaskCacheStorage getInstance() {
        if (instance == null)
            instance = new PlayEduTaskCacheStorage();

        return instance;
    }

    // Получение всех задач
    public ArrayList<PlayEduTask> getTasks() {
        return tasks;
    }

    // Добавление задачи
    public void addTask(PlayEduTask task) {
        tasks.add(task);
    }

    // Редактирование задачи
    public boolean updateTask(int taskId, PlayEduTask newTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                tasks.set(i, newTask);
                return true;
            }
        }
        return false;
    }

    // Удаление задачи
    public boolean deleteTask(PlayEduTask task) {
        return tasks.remove(task);
    }
}
