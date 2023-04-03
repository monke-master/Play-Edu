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

    // Возвращает экземпляр класса
    // Если экземпляра нет, создает новый
    private static PlayEduTaskCacheStorage getInstance() {
        if (instance == null)
            instance = new PlayEduTaskCacheStorage();

        return instance;
    }

    // Возвращает задачу с заданным id, если она есть в списке
    // Иначе возвращает null
    public PlayEduTask getTaskById(int id) {
        for (PlayEduTask task: tasks) {
            if (task.getTaskId() == id)
                return task;
        }
        return null;
    }

    // Возвращает список задач
    public ArrayList<PlayEduTask> getTasks() {
        return tasks;
    }

    // Добавляет задачу
    public void addTask(PlayEduTask task) {
        tasks.add(task);
    }

    // Заменяет задачу с заданным id на новую
    public boolean updateTask(int taskId, PlayEduTask newTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                tasks.set(i, newTask);
                return true;
            }
        }
        return false;
    }

    // Удаляет задачу с заданным id
    public boolean deleteTask(PlayEduTask task) {
        return tasks.remove(task);
    }
}
