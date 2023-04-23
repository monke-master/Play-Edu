package ru.mirea.playedu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import ru.mirea.playedu.data.repository.CategoryRepository;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.data.storage.cache.CategoryCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserTaskCacheStorage;
import ru.mirea.playedu.model.UserTask;

public class TasksViewModel extends ViewModel {
    // Объявление репозитория для пользовательских заданий
    private final UserTaskRepository userTaskRepository;
    // Объявление репозитория для пользовательских категорий
    private CategoryRepository categoryRepository;
    //
    private final MutableLiveData<ArrayList<UserTask>> filteredList = new MutableLiveData<>();
    private ArrayList<Calendar> dateList;
    private int todayDatePosition;

    public TasksViewModel() {
        userTaskRepository = new UserTaskRepository(UserTaskCacheStorage.getInstance());
        categoryRepository = new CategoryRepository(CategoryCacheStorage.getInstance());
        dateList = new ArrayList<>();

        fillDateList();
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

    // Заполнение массива дат в диапазоне (-год от сегодняшней даты; +год от сегодняшней)
    private void fillDateList() {
        Date today = Calendar.getInstance().getTime();
        // Получение даты, равной текущей минус год
        Calendar yearAgo = Calendar.getInstance();
        yearAgo.add(Calendar.YEAR, -1);
        // Получение даты, равной текущей плюс год
        Calendar yearLater = Calendar.getInstance();
        yearLater.add(Calendar.YEAR, +1);

        for (Calendar date = yearAgo;
             date.getTime().getTime() <= yearLater.getTime().getTime();
             date.add(Calendar.DAY_OF_MONTH, 1)) {
                Calendar temp = (Calendar) date.clone();
                dateList.add(temp);
                if (temp.getTime().getTime() == today.getTime())
                    todayDatePosition = dateList.size() - 1;
        }
    }

    public ArrayList<Calendar> getDateList() {
        return dateList;
    }

    public int getTodayDayPosition() {
        return todayDatePosition;
    }
}
