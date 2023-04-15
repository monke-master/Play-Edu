package ru.mirea.playedu.usecases;

import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.model.User;

public class GetUserUseCase {

    private UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute() {
        return userRepository.getUser();
    }
}
