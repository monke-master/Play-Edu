package ru.mirea.playedu.usecases;


import ru.mirea.playedu.Constants.PowerStatus;
import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.model.Power;

public class DisablePowerUseCase {
    PowerRepository powerRepository;

    public DisablePowerUseCase(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    public void execute(Power power) {
        power.setPowerStatus(PowerStatus.USED);
        powerRepository.updatePower(power.getPowerId(), power);
    }
}
