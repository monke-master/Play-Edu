package ru.mirea.playedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ru.mirea.playedu.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegistrationBinding binding = ActivityRegistrationBinding.inflate(getLayoutInflater());

        binding.signUpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, ChooseHeroActivity.class);
            startActivity(intent);
        });

        binding.toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, LaunchActivity.class);
            startActivity(intent);
        });

        setContentView(binding.getRoot());
    }
}