package ru.mirea.playedu.view.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;

import ru.mirea.playedu.R;
import ru.mirea.playedu.data.repository.UserStatsRepository;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.data.storage.cache.CategoryCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserStatsCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserTaskCacheStorage;
import ru.mirea.playedu.databinding.ActivityEnterBinding;
import ru.mirea.playedu.model.Category;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.model.UserStats;
import ru.mirea.playedu.model.UserTask;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEnterBinding binding = ActivityEnterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Возвращение на предыдущую активность
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.enterBtn.setOnClickListener(v -> {
            // TODO убрать нахер
            User user = new User("Пропастин Алексей Федорович", "passwordCOmrade",
                    "+89690776", "ИВБО-01-22", 100, 1000, "no", new ArrayList<>());
            UserCacheStorage cacheStorage = UserCacheStorage.getInstance();
            UserStats userStats = new UserStats(1, Calendar.getInstance().getTime(), 10, 11, 0, 12, 0, 0);
            UserStatsCacheStorage storage = UserStatsCacheStorage.getInstance();
            storage.setUserStats(userStats);
            cacheStorage.setUser(user);

            CategoryCacheStorage categoryCacheStorage = CategoryCacheStorage.getInstance();
            Category category = new Category("Задачи по пропастинизации");
            Category category1 = new Category("Задачи по дискретизации");
            Category category2 = new Category("Задачи по тригерризации");
            Category category3 = new Category("Задачи по авроризации");
            categoryCacheStorage.addCategory(category1);
            categoryCacheStorage.addCategory(category);
            categoryCacheStorage.addCategory(category2);
            categoryCacheStorage.addCategory(category3);

            Calendar today = Calendar.getInstance();
            Calendar date1 = Calendar.getInstance();
            date1.add(Calendar.DAY_OF_MONTH, -2);
            Calendar date2 = Calendar.getInstance();
            date2.add(Calendar.DAY_OF_MONTH, 1);


            UserTaskCacheStorage taskCacheStorage = UserTaskCacheStorage.getInstance();
            UserTask userTask1 = new UserTask("построить социализм", category, false, 10,
                    Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), getResources().getColor(R.color.golden));
            UserTask userTask2 = new UserTask("построить пропастинизм", category, false, 100,
                    Calendar.getInstance().getTime(), date2.getTime(), getResources().getColor(R.color.white));

            UserTask userTask3 = new UserTask("захватить Ригу", category1, false, 1,
                    Calendar.getInstance().getTime(), date1.getTime(), getResources().getColor(R.color.black));
            UserTask userTask4 = new UserTask("взять берлин", category1, false, 2,
                    Calendar.getInstance().getTime(), today.getTime(), getResources().getColor(R.color.white));
            UserTask userTask5 = new UserTask("азъебать воронеж", category1, false, 2,
                    Calendar.getInstance().getTime(), date2.getTime(), getResources().getColor(R.color.golden));

            taskCacheStorage.addTask(userTask1);
            taskCacheStorage.addTask(userTask2);
            taskCacheStorage.addTask(userTask3);
            taskCacheStorage.addTask(userTask4);
            taskCacheStorage.addTask(userTask5);


            Intent intent = new Intent(EnterActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}