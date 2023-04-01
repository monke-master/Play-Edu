package ru.mirea.playedu.data.repository;

import java.util.ArrayList;

import ru.mirea.playedu.data.storage.cache.PlayEduTaskCacheStorage;
import ru.mirea.playedu.model.PlayEduTask;
import ru.mirea.playedu.model.RepositoryResponse;
import ru.mirea.playedu.model.UserTask;

public class PlayEduTaskRepository {

    private final PlayEduTaskCacheStorage cacheStorage;

    public PlayEduTaskRepository(PlayEduTaskCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    public ArrayList<PlayEduTask> getTasks() {
        return cacheStorage.getTasks();
    }

    public RepositoryResponse addTask(PlayEduTask task) {
        cacheStorage.addTask(task);
        return new RepositoryResponse(200, "Success");
    }

    public RepositoryResponse deleteTask(PlayEduTask task) {
        if (cacheStorage.deleteTask(task))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }

    public RepositoryResponse updateTask(int taskId, PlayEduTask newTask) {
        if (cacheStorage.updateTask(taskId, newTask))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }
}
