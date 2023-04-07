package ru.mirea.playedu.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.CategoryRepository;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.data.storage.cache.CategoryCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserTaskCacheStorage;
import ru.mirea.playedu.model.Category;
import ru.mirea.playedu.model.UserTask;
import ru.mirea.playedu.usecases.AddTaskUseCase;

public class AddTaskViewModel extends ViewModel {
    // Объявление use case для добавления задания
    private AddTaskUseCase addTaskUseCase;
    // Объявление репозитория для пользовательских заданий
    private UserTaskRepository userTaskRepository;
    // Объявление репозитория для пользовательских категорий
    private CategoryRepository categoryRepository;

    public AddTaskViewModel() {
        userTaskRepository = new UserTaskRepository(UserTaskCacheStorage.getInstance());
        categoryRepository = new CategoryRepository(CategoryCacheStorage.getInstance());
        addTaskUseCase = new AddTaskUseCase(userTaskRepository);
    }

    // Функция добавления задания
    public void addTask(UserTask task) {
        addTaskUseCase.execute(task);
    }

    public Category createCategory(String categoryName) {
        ArrayList<Category> categories = categoryRepository.getCategories();

        for (Category category : categories) {
            if (category.getTitle() == categoryName) {
                return category;
            }
        }
        Category newCategory = new Category(categoryName);
        categoryRepository.addCategory(newCategory);
        return newCategory;
    }
}
