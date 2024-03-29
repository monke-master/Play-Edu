package ru.mirea.playedu.data.repository;

import java.util.ArrayList;

import ru.mirea.playedu.data.storage.cache.PlayEduTaskCacheStorage;
import ru.mirea.playedu.model.PlayEduTask;
import ru.mirea.playedu.model.Response;

// Репозиторий для хранения задач от системы
public class PlayEduTaskRepository {

    private final PlayEduTaskCacheStorage cacheStorage;

    public PlayEduTaskRepository(PlayEduTaskCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    // Возвращает список задач
    public ArrayList<PlayEduTask> getTasks() {
        return cacheStorage.getTasks();
    }

    // Возвращает задачу с заданным id, если она есть в списке
    // Иначе возвращает null
    public PlayEduTask getTaskById(int id) {
        return cacheStorage.getTaskById(id);
    }

    // Добавляет задачу
    public Response addTask(PlayEduTask task) {
        cacheStorage.addTask(task);
        return new Response(200, "Success");
    }

    // Удаляет задачу с заданным id
    public Response deleteTask(PlayEduTask task) {
        if (cacheStorage.deleteTask(task))
            return new Response(200, "Success");
        else
            return new Response(404, "Not found");
    }

    // Обновляет задачу с заданным id
    public Response updateTask(int taskId, PlayEduTask newTask) {
        if (cacheStorage.updateTask(taskId, newTask))
            return new Response(200, "Success");
        else
            return new Response(404, "Not found");
    }
}
