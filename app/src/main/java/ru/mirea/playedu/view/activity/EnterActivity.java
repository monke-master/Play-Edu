package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import ru.mirea.playedu.databinding.ActivityEnterBinding;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEnterBinding binding = ActivityEnterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Возвращение на предыдущую активность
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.enterBtn.setOnClickListener(v -> {
            Intent intent = new Intent(EnterActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}