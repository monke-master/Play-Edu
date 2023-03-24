package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ru.mirea.playedu.databinding.ActivityRegistrationBinding;

// Активность с формой регистрации
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegistrationBinding binding = ActivityRegistrationBinding.inflate(getLayoutInflater());

        binding.signUpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, ChooseHeroActivity.class);
            startActivity(intent);
        });

        // Возвращение на предыдущую активность
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        setContentView(binding.getRoot());
    }
}