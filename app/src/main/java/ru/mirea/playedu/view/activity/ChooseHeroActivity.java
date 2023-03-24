package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.ActivityChooseHeroBinding;

public class ChooseHeroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChooseHeroBinding binding = ActivityChooseHeroBinding.inflate(getLayoutInflater());

        // Возвращение на предыдущую активность
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        setContentView(binding.getRoot());
    }
}