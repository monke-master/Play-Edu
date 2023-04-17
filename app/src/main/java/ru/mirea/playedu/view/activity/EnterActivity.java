package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;

import ru.mirea.playedu.data.repository.UserStatsRepository;
import ru.mirea.playedu.data.storage.cache.UserCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserStatsCacheStorage;
import ru.mirea.playedu.databinding.ActivityEnterBinding;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.model.UserStats;

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
            UserStats userStats = new UserStats(1, Calendar.getInstance().getTime());
            UserStatsCacheStorage storage = UserStatsCacheStorage.getInstance();
            storage.setUserStats(userStats);
            cacheStorage.setUser(user);
            Intent intent = new Intent(EnterActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}