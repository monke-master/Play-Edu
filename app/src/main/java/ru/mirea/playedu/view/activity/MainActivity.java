package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import ru.mirea.playedu.data.storage.cache.UserCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserStatsCacheStorage;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.model.UserStats;
import ru.mirea.playedu.view.dialog.EnemyPreviewDialog;
import ru.mirea.playedu.view.dialog.StartGameDialog;
import ru.mirea.playedu.view.fragment.CommunityFragment;
import ru.mirea.playedu.view.fragment.ProfileFragment;
import ru.mirea.playedu.view.fragment.QuestsFragment;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.ActivityMainBinding;

// Базовая активность
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        EnemyPreviewDialog enemyPreviewDialog = new EnemyPreviewDialog();
        enemyPreviewDialog.show(getSupportFragmentManager(), "aboibus");

        // Отслеживание переключения вкладок
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.quests_btn:
                    replaceFragment(new QuestsFragment());
                    break;
                case R.id.profile_btn:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.community_btn:
                    replaceFragment(new CommunityFragment());
                    break;
            }
            return true;

        });

        UserStatsCacheStorage userStatsCacheStorage = UserStatsCacheStorage.getInstance();
        UserCacheStorage userCacheStorage = UserCacheStorage.getInstance();

        User user = userCacheStorage.getUser();
        UserStats userStats = userStatsCacheStorage.getUserStats();

        Log.d("Cat", user.toString() + " " + userStats.toString());

        setContentView(binding.getRoot());
    }


    // Переключение фрагмента
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
    }
}