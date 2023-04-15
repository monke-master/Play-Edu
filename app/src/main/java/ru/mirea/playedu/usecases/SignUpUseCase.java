package ru.mirea.playedu.usecases;

import java.util.Calendar;

import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.data.repository.UserStatsRepository;
import ru.mirea.playedu.model.Response;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.model.UserStats;


// Use-Case для регистрации по номеру телефона
public class SignUpUseCase {

    private UserRepository userRepository;
    private UserStatsRepository userStatsRepository;

    public SignUpUseCase(UserRepository userRepository, UserStatsRepository userStatsRepository) {
        this.userRepository = userRepository;
        this.userStatsRepository = userStatsRepository;
    }

    // Выполняет Use-Case
    public Response execute(User user) {
        // Создание учетной записи пользователя
        Response userRepoResponse = userRepository.createUser(user);
        // В случае неудачи возвращает ошибку
        if (userRepoResponse.getCode() != 200)
            return userRepoResponse;
        // Создание статистики пользователя с нулевыми показателями
        int userId = (int)userRepoResponse.getResponseObject();
        UserStats userStats = new UserStats(userId, Calendar.getInstance().getTime());
        // Возвращает результат
        return userStatsRepository.setUserStats(userStats);
    }
}
