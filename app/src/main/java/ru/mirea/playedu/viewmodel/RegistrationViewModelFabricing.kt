package ru.mirea.playedu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mirea.playedu.data.repository.UserRepository
import ru.mirea.playedu.data.repository.UserStatsRepository

class RegistrationViewModelFabricing (val userRepository: UserRepository, val userStatsRepository: UserStatsRepository) :
    ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegistrationViewModel(userRepository, userStatsRepository) as T
        }
    }
