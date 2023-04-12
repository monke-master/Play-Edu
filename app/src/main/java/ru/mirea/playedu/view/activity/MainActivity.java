package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import ru.mirea.playedu.view.dialog.DamageDialog;
import ru.mirea.playedu.view.dialog.EnemyPreviewDialog;
import ru.mirea.playedu.view.dialog.FightEndDialog;
import ru.mirea.playedu.view.dialog.StartGameDialog;
import ru.mirea.playedu.view.dialog.TextDialog;
import ru.mirea.playedu.view.fragment.CommunityFragment;
import ru.mirea.playedu.view.fragment.GameFragment;
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

        // Отслеживание переключения вкладок
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.quests_btn:
                    replaceFragment(new QuestsFragment());
                    break;
                case R.id.adventure_btn:
                    replaceFragment(new GameFragment());
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
        
        setContentView(binding.getRoot());
    }


    // Переключение фрагмента
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
    }
}