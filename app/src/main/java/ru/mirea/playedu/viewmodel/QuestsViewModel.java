package ru.mirea.playedu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Objects;

import ru.mirea.playedu.data.repository.CategoryRepository;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.data.storage.cache.CategoryCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserTaskCacheStorage;
import ru.mirea.playedu.model.UserTask;

public class QuestsViewModel extends ViewModel {
    // Объявление репозитория для пользовательских заданий
    private final UserTaskRepository userTaskRepository;
    // Объявление репозитория для пользовательских категорий
    private CategoryRepository categoryRepository;
    //
    private final MutableLiveData<ArrayList<UserTask>> filteredList = new MutableLiveData<>();

    public QuestsViewModel() {
        userTaskRepository = new UserTaskRepository(UserTaskCacheStorage.getInstance());
        categoryRepository = new CategoryRepository(CategoryCacheStorage.getInstance());
    }

    public ArrayList<UserTask> getAllTasksList() { return userTaskRepository.getTasks(); }

    // Задание отфильтрованного списка по заданной категории
    public void setTasksListForCategory(String category) {
        ArrayList<UserTask> filteredTasks = new ArrayList<UserTask>();
        for (UserTask userTask : userTaskRepository.getTasks()) {
            if (Objects.equals(userTask.getCategory().getTitle(), category)) {
                filteredTasks.add(userTask);
            }
        }
        filteredList.setValue(filteredTasks);
    }

    // Задание отфильтрованного списка по заданному цвету
    public void setTasksListForColor(int color) {
        ArrayList<UserTask> filteredTasks = new ArrayList<UserTask>();
        for (UserTask userTask : userTaskRepository.getTasks()) {
            if (userTask.getColor() == color) {
                filteredTasks.add(userTask);
            }
        }
        filteredList.setValue(filteredTasks);
    }

    public LiveData<ArrayList<UserTask>> getFilteredList() {
        return filteredList;
    }

    public ArrayList<String> getCategories() {
        return categoryRepository.getCategoriesTitles();
    }
}
