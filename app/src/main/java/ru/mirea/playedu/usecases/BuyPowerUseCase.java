package ru.mirea.playedu.usecases;

import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.Response;

// UseCase покупки силы
public class BuyPowerUseCase {

    private PowerRepository powerRepository;

    public BuyPowerUseCase(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    public Response execute(Power power) {
        power.setBought(true);
        return powerRepository.updatePower(power.getPowerId(), power);
    }
}
