package ru.mirea.playedu.view_model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.mirea.playedu.BR;
import ru.mirea.playedu.data.repository.CategoryRepository;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.data.storage.cache.CategoryCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserTaskCacheStorage;
import ru.mirea.playedu.model.Category;
import ru.mirea.playedu.model.UserTask;

public class QuestsViewModel extends ViewModel {
    // Объявление репозитория для пользовательских заданий
    private UserTaskRepository userTaskRepository;
    // Объявление репозитория для пользовательских категорий
    private CategoryRepository categoryRepository;

    public QuestsViewModel() {
        userTaskRepository = new UserTaskRepository(UserTaskCacheStorage.getInstance());
        categoryRepository = new CategoryRepository(CategoryCacheStorage.getInstance());
    }

    public ArrayList<UserTask> getTaskList() {
        return userTaskRepository.getTasks();
    }

    public ArrayList<Category> getCategoryList() {
        return categoryRepository.getCategories();
    }


}