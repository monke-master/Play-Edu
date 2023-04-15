package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import ru.mirea.playedu.databinding.ActivityRegistrationBinding;
import ru.mirea.playedu.viewmodel.RegistrationViewModel;
import ru.mirea.playedu.viewmodel.RegistrationViewModelFabric;

// Активность с формой регистрации
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegistrationBinding binding = ActivityRegistrationBinding.inflate(getLayoutInflater());

        // Получение view-model
        RegistrationViewModel viewModel = ViewModelProviders.of(this,
                new RegistrationViewModelFabric()).get(RegistrationViewModel.class);
        binding.setViewModel(viewModel);

        // Отслеживание нажатия на кнопку регистрации
        // При нажатии переходит на следующую активность
        binding.signUpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, ChooseHeroActivity.class);
            startActivity(intent);
        });

        // Возвращение на предыдущую активность
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        setContentView(binding.getRoot());
    }
}