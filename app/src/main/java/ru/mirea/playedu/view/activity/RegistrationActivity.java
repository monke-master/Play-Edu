package ru.mirea.playedu.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import ru.mirea.playedu.Constants;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.ActivityRegistrationBinding;
import ru.mirea.playedu.viewmodel.RegistrationViewModel;
import ru.mirea.playedu.viewmodel.RegistrationViewModelFactory;

// Активность с формой регистрации
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegistrationBinding binding = ActivityRegistrationBinding.inflate(getLayoutInflater());

        // Получение view-model
        RegistrationViewModel viewModel = ViewModelProviders.of(this,
                new RegistrationViewModelFactory()).get(RegistrationViewModel.class);
        binding.setViewModel(viewModel);

        // Отслеживание соответствия введенного пароля заданным стандартам после ввода
        binding.editTxtPswrd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString();
                if (password.length() < Constants.MIN_PASSWORD_LENGTH)
                    binding.editTxtPswrd.setError(getString(R.string.min_pswrd_length));
                if (password.length() > Constants.MAX_PASSWORD_LENGTH)
                    binding.editTxtPswrd.setError(getString(R.string.max_pswrd_length));
            }
        });

        // Отслеживание совпадения пароля и его повтора после ввода
        binding.editTxtPswrdRep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String repeatedPassword = editable.toString();
               if (!repeatedPassword.equals(viewModel.getPassword().getValue()))
                   binding.editTxtPswrdRep.setError(getString(R.string.password_match_error));
            }
        });



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