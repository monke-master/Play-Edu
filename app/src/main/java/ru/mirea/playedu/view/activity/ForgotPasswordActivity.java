package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityForgotPasswordBinding binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());

        // Возвращение на предыдущую активность
        binding.toolbar.setNavigationOnClickListener(view -> finish());

        setContentView(binding.getRoot());


    }
}