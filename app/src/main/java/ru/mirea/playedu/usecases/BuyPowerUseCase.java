package ru.mirea.playedu.usecases;

import ru.mirea.playedu.Constants;
import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.Response;
import ru.mirea.playedu.model.User;

// UseCase покупки силы
public class BuyPowerUseCase {

    private PowerRepository powerRepository;
    private UserRepository userRepository;

    public BuyPowerUseCase(PowerRepository powerRepository, UserRepository userRepository) {
        this.powerRepository = powerRepository;
        this.userRepository = userRepository;
    }

    // В случае успеха возвращает в теле ответа измененного пользователя
    public Response execute(Power power) {
        // Получение данных пользователя
        User user = userRepository.getUser();
        // Снятие с баланса пользователя нужной суммы
        if (power.getPriceType() == Constants.SILVER_COINS_TYPE)
            user.setSilverCoins(user.getSilverCoins() - power.getPrice());
        else
            user.setGoldenCoins(user.getGoldenCoins() - power.getPrice());
        // Установка флажка покупки
        power.setBought(true);
        // Обновление данных пользователя
        Response userRepoResponse = userRepository.updateUser(user);
        if (userRepoResponse.getCode() != 200)
            return userRepoResponse;
        // Обновление данных силы
        Response powerRepoResponse = powerRepository.updatePower(power.getPowerId(), power);
        // Откат изменений в случае ошибки
        if (powerRepoResponse.getCode() != 200) {
            if (power.getPriceType() == Constants.SILVER_COINS_TYPE)
                user.setSilverCoins(user.getSilverCoins() + power.getPrice());
            else
                user.setGoldenCoins(user.getGoldenCoins() + power.getPrice());
        }
        userRepoResponse.setResponseObject(user);
        return userRepoResponse;
    }
}
