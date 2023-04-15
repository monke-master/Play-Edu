package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import ru.mirea.playedu.Constants;
import ru.mirea.playedu.databinding.ActivityChooseHeroBinding;
import ru.mirea.playedu.viewmodel.RegistrationViewModel;
import ru.mirea.playedu.viewmodel.RegistrationViewModelFactory;

// Активность с выбором персонажа
public class ChooseHeroActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChooseHeroBinding binding = ActivityChooseHeroBinding.inflate(getLayoutInflater());
        // Получение view-model
        viewModel = ViewModelProviders.of(this,
                new RegistrationViewModelFactory()).get(RegistrationViewModel.class);

        // Отслеживание выбора персонажа
        // После выбора вызывает регистрацию
        binding.maleChooseBtn.setOnClickListener(view -> {
            viewModel.chooseHero(Constants.MALE_IC);
            signUp();
        });

        binding.femaleChooseBtn.setOnClickListener(view -> {
            viewModel.chooseHero(Constants.FEMALE_IC);
            signUp();
        });

        // Возвращение на предыдущую активность
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        setContentView(binding.getRoot());
    }

    // Вызывает метод регистрации
    // В случае успеха переходит на главную активность
    // Иначе выводит toast с ошибкой
    private void signUp() {
        int code = viewModel.signUpWithPhone();
        if (code == 200) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), viewModel.getToastMessage(), Toast.LENGTH_LONG);
        }
    }
}