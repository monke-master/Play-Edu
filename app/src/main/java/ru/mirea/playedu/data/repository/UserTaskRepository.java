package ru.mirea.playedu.data.repository;

import java.util.ArrayList;


import ru.mirea.playedu.data.storage.cache.UserTaskCacheStorage;
import ru.mirea.playedu.model.Response;
import ru.mirea.playedu.model.UserTask;

// Репозиторий для хранения пользовательских задач
public class UserTaskRepository {

    private final UserTaskCacheStorage cacheStorage;

    public UserTaskRepository(UserTaskCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    // Возвращает задачу с заданным id, если она есть в списке
    // Иначе возвращает null
    public UserTask getTaskById(int id) {
        return cacheStorage.getTaskById(id);
    }

    // Возвращает список пользовательских задач
    public ArrayList<UserTask> getTasks() {
        return cacheStorage.getTasks();
    }

    // Добавляет задачу
    public Response addTask(UserTask task) {
        cacheStorage.addTask(task);
        return new Response(200, "Success");
    }

    // Удаляет задачу с заданным id
    public Response deleteTask(UserTask task) {
        if (cacheStorage.deleteTask(task))
            return new Response(200, "Success");
        else
            return new Response(404, "Not found");
    }

    // Заменяет задачу с заданным id на новую
    public Response updateTask(int taskId, UserTask newTask) {
        if (cacheStorage.updateTask(taskId, newTask))
            return new Response(200, "Success");
        else
            return new Response(404, "Not found");
    }
}
