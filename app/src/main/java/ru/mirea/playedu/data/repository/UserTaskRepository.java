package ru.mirea.playedu.data.repository;

import java.util.ArrayList;


import ru.mirea.playedu.data.storage.cache.UserTaskCacheStorage;
import ru.mirea.playedu.model.RepositoryResponse;
import ru.mirea.playedu.model.UserTask;

public class UserTaskRepository {

    private final UserTaskCacheStorage cacheStorage;

    public UserTaskRepository(UserTaskCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    public ArrayList<UserTask> getTasks() {
        return cacheStorage.getTasks();
    }

    public RepositoryResponse addTask(UserTask task) {
        cacheStorage.addTask(task);
        return new RepositoryResponse(200, "Success");
    }

    public RepositoryResponse deleteTask(UserTask task) {
        if (cacheStorage.deleteTask(task))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }

    public RepositoryResponse updateTask(int taskId, UserTask newTask) {
        if (cacheStorage.updateTask(taskId, newTask))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }
}
