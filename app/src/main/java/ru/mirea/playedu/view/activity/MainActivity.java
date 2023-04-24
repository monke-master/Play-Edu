package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import ru.mirea.playedu.Constants;
import ru.mirea.playedu.data.storage.cache.AchievementCacheStorage;
import ru.mirea.playedu.data.storage.cache.PowerCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserStatsCacheStorage;
import ru.mirea.playedu.model.Achievement;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.model.UserStats;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.ActivityMainBinding;

// Базовая активность
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);


        // TODO убрать это нахуй
        // Перевiрка регистрации
        UserStatsCacheStorage userStatsCacheStorage = UserStatsCacheStorage.getInstance();
        UserCacheStorage userCacheStorage = UserCacheStorage.getInstance();

        User user = userCacheStorage.getUser();
        UserStats userStats = userStatsCacheStorage.getUserStats();

        Log.d("Cat", user.toString() + " " + userStats.toString());

        // Мокаем ачивки (осуждаю)
        AchievementCacheStorage cacheStorage = AchievementCacheStorage.getInstance();
        for (Achievement achievement: Constants.getAchievementsList(this)) {
            cacheStorage.addAchievement(achievement);
        }

        // Мокаем силы (осуждаю)
        PowerCacheStorage cacheStorage1 = PowerCacheStorage.getInstance();
        for (Power power: Constants.getPowersList(this)) {
            cacheStorage1.addPower(power);
        }

        setContentView(binding.getRoot());
    }
}